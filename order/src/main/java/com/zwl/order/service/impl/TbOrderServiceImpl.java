package com.zwl.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.kmall.pojo.TbItem;
import com.kmall.pojo.TbOrder;
import com.kmall.pojo.TbOrderItem;
import com.kmall.pojo.TbOrderShipping;
import com.kmall.redis.dao.JedisDao;
import com.kmall.service.TbItemDubboService;
import com.kmall.service.TbOrderDubboService;
import com.zwl.commons.pojo.EgoResult;
import com.zwl.commons.pojo.TbItemChild;
import com.zwl.commons.utils.CookieUtils;
import com.zwl.commons.utils.HttpClientUtil;
import com.zwl.commons.utils.IDUtils;
import com.zwl.commons.utils.JsonUtils;
import com.zwl.order.pojo.MyOrderParam;
import com.zwl.order.service.TbOrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class TbOrderServiceImpl implements TbOrderService {
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.cart.key}")
	private String cartKey;
	@Value("${passport.url}")
	private String passprtUrl;
	@Reference(version = "1.0.0")
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference(version = "1.0.0")
	private TbOrderDubboService tbOrderDubboService;
	@Override
	public List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request) {
		//根据cookie取得redis得key
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		//发送这个key去这个项目下这个控制器下查找对应得客户信息
		String result = HttpClientUtil.doPost(passprtUrl+token);
		EgoResult er = JsonUtils.jsonToPojo(result, EgoResult.class);
		//获取到 购物车信息在redis对应得key
		String key = cartKey+((LinkedHashMap)er.getData()).get("username");
		String json = jedisDaoImpl.get(key);
		//转化成对应得pojo对象
		List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
		
		//对比库存量是否足够
		List<TbItemChild> listNew = new ArrayList<>();
		for (TbItemChild child : list) {
			for (Long id : ids) {
				if((long)child.getId()==(long)id){
					//判断购买量是否大于等于库存
					TbItem tbItem = tbItemDubboServiceImpl.selById(id);
					if(tbItem.getNum()>=child.getNum()){
						//库存够得
						child.setEnough(true);
					}else{
						//库存不够
						child.setEnough(false);
					}
					//添加信息
					listNew.add(child);
				}
				
			}
		}
		//返回信息
		return listNew;
	}
	@Override
	public EgoResult create(MyOrderParam param, HttpServletRequest request) {
		//订单表数据
		TbOrder order = new TbOrder();
		order.setPayment(param.getPayment());
		order.setPaymentType(param.getPaymentType());
		long id = IDUtils.genItemId();
		order.setOrderId(id+"");
		Date date =new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String result = HttpClientUtil.doPost(passprtUrl+token);
		EgoResult er = JsonUtils.jsonToPojo(result, EgoResult.class);

		Map user= (LinkedHashMap)er.getData();
		order.setUserId(Long.parseLong(user.get("id").toString()));


		List<TbOrder> tbOrders= tbOrderDubboService.selAllOrderByUseID(Long.parseLong(user.get("id").toString()));
		System.out.println("tbOrders size"+tbOrders.size());

		/*查询用户对应的订单*/
		System.out.println(tbOrders.size());
		er.setData(tbOrders);

		order.setBuyerNick(user.get("username").toString());
		order.setBuyerRate(0);
		order.setStatus(2);
		//订单-商品表
		for (TbOrderItem item : param.getOrderItems()) {
			item.setId(IDUtils.genItemId()+"");
			item.setOrderId(id+"");
		}
		//收货人信息
		TbOrderShipping shipping = param.getOrderShipping();
		shipping.setOrderId(id+"");
		shipping.setCreated(date);
		shipping.setUpdated(date);
		
		EgoResult erResult = new EgoResult();
		erResult.setData(tbOrders);
		try {
			int index = tbOrderDubboService.insOrder(order, param.getOrderItems(), shipping);
			if(index>0){
				erResult.setStatus(200);

				//查询这个用户所有的订单信息

				//删除购买的商品，从购物车中删除购买得商品
				String json = jedisDaoImpl.get(cartKey+user.get("username"));
				List<TbItemChild> listCart = JsonUtils.jsonToList(json, TbItemChild.class);
				List<TbItemChild> listNew = new ArrayList<>();
				for (TbItemChild child : listCart) {
					for (TbOrderItem item : param.getOrderItems()) {
						System.out.println("1"+child.getId().longValue());
						System.out.println("2"+Long.parseLong(item.getItemId()));
						
						if(child.getId().longValue()==Long.parseLong(item.getItemId())){
							listNew.add(child);
						}
					}
				}
				for (TbItemChild mynew : listNew) {
					listCart.remove(mynew);
				}
				jedisDaoImpl.set(cartKey+user.get("username"), JsonUtils.objectToJson(listCart));
				//删除
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return erResult;
	}

	@Override
	public List<TbOrder> selAllOrderByUseID(long user_id) {
		return tbOrderDubboService.selAllOrderByUseID(user_id);
	}

	@Override
	public List<TbOrderItem> selAllOrderItemByOrderId(String order_id) {
		return tbOrderDubboService.selAllOrderItemByOrderId(order_id);
	}


}
