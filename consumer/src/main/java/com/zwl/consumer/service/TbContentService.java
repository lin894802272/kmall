package com.zwl.consumer.service;


import com.kmall.pojo.TbContent;
import com.zwl.commons.pojo.EasyUIDataGrid;

public interface TbContentService {
	/**
	 * 分页显示内容信息
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showContent(long categoryId, int page, int rows);
	/**
	 * 新增内容
	 * @param content
	 * @return
	 */
	int save(TbContent content);
}
