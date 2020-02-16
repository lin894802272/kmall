package com.zwl.order.service;

import com.kmall.pojo.TbOrder;
import com.kmall.pojo.TbOrderExample;
import com.kmall.pojo.TbOrderItem;
import com.kmall.pojo.TbOrderItemExample;
import com.zwl.commons.pojo.EgoResult;
import com.zwl.commons.pojo.TbItemChild;
import com.zwl.order.pojo.MyOrderParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;



public interface TbOrderService {
	/**
	 * 确认订单信息包含的商品
	 * @param id
	 * @return
	 */
	List<TbItemChild> showOrderCart(List<Long> id, HttpServletRequest request);
	/**
	 * 创建订单
	 * @param param
	 * @return
	 */
	EgoResult create(MyOrderParam param, HttpServletRequest request);






	/**
	 * 根据用户ID查询所有的订单
	 */
	public List<TbOrder> selAllOrderByUseID(long user_id);


	/**
	 * 根据订单编号查询订单对应的商品
	 */
	public List<TbOrderItem> selAllOrderItemByOrderId(String order_id);

}
