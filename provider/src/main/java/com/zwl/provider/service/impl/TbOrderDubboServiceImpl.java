package com.zwl.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kmall.pojo.*;
import com.kmall.service.StudentService;
import com.kmall.service.TbOrderDubboService;
import com.zwl.provider.mapper.TbOrderItemMapper;
import com.zwl.provider.mapper.TbOrderMapper;
import com.zwl.provider.mapper.TbOrderShippingMapper;

import java.util.List;

import javax.annotation.Resource;
@Service(timeout =4000, version = "1.0.0",interfaceClass = TbOrderDubboService.class)   //dubbo service注解，需要指定版本,并告述它要实现那个接口,然后消费者消费的时候，需要指定版本号
@org.springframework.stereotype.Service
public class TbOrderDubboServiceImpl implements TbOrderDubboService {
	@Resource
	private TbOrderMapper tbOrderMapper;
	@Resource
	private TbOrderItemMapper tbOrderItemMapper;
	@Resource
	private TbOrderShippingMapper tbOrderShippingMapper;
	@Override
	public int insOrder(TbOrder order, List<TbOrderItem> list, TbOrderShipping shipping) throws Exception {
		int index = tbOrderMapper.insertSelective(order);
		for (TbOrderItem tbOrderItem : list) {
			index+=tbOrderItemMapper.insertSelective(tbOrderItem);
		}
		index+=tbOrderShippingMapper.insertSelective(shipping);
		if(index==2+list.size()){
			return 1;
		}else{
			throw new Exception("创建订单失败");
		}
	}

	@Override
	/**
	 * 根据用户ID查询所有的订单
	 */
	public List<TbOrder> selAllOrderByUseID(long user_id) {
			TbOrderExample tbOrderExample=new TbOrderExample();
		tbOrderExample.createCriteria().andUserIdEqualTo(user_id);
		List<TbOrder> tbOrders = tbOrderMapper.selectByExample(tbOrderExample);
		return tbOrders;
	}


	@Override
	/**
	 * 根据订单编号查询订单对应的商品
	 */
	public List<TbOrderItem> selAllOrderItemByOrderId(String order_id) {
		TbOrderItemExample tbOrderItemExample=new TbOrderItemExample();
		tbOrderItemExample.createCriteria().andOrderIdEqualTo(order_id);
		return tbOrderItemMapper.selectByExample(tbOrderItemExample);
	}

}
