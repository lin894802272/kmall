package com.kmall.service;

import com.kmall.pojo.TbOrder;
import com.kmall.pojo.TbOrderItem;
import com.kmall.pojo.TbOrderShipping;

import java.util.List;


public interface TbOrderDubboService {
	/**
	 * 创建订单
	 * @param order
	 * @param list
	 * @param shipping
	 * @return
	 */
	int insOrder(TbOrder order, List<TbOrderItem> list, TbOrderShipping shipping) throws Exception;


	/**
	 * 根据用户ID查询他所有的订单
	 * @param user_id
	 * @return
	 */
	List<TbOrder> selAllOrderByUseID(long user_id);


	/**
	 * 根据订单编号，查询对应的商品
	 * @param order_id
	 * @return
	 */
	List<TbOrderItem> selAllOrderItemByOrderId(String order_id);



}
