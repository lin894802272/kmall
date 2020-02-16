package com.zwl.cart.service;

import com.zwl.commons.pojo.EgoResult;
import com.zwl.commons.pojo.TbItemChild;

import java.util.List;

import javax.servlet.http.HttpServletRequest;



public interface CartService {
	/**
	 * 加入购物车
	 * @param id
	 * @param num
	 */
	void addCart(long id, int num, HttpServletRequest request);
	/**
	 * 显示购物车
	 * @return
	 */
	List<TbItemChild> showCart(HttpServletRequest request);
	/**
	 * 根据id修改数量
	 * @param id
	 * @param num
	 * @return
	 */
	EgoResult update(long id, int num, HttpServletRequest request) ;
	/**
	 * 删除购物车商品
	 * @param id
	 * @param req
	 * @return
	 */
	EgoResult delete(long id, HttpServletRequest req);
}
