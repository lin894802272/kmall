package com.kmall.service;


import com.kmall.pojo.TbItemParamItem;

public interface TbItemParamItemDubboSerice {
	/**
	 * 根据商品id查询商品规格参数
	 * @param itemId
	 * @return
	 */
	TbItemParamItem selByItemid(long itemId);
}
