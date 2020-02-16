package com.zwl.consumer.service;

import com.kmall.pojo.TbContentCategory;
import com.zwl.commons.pojo.EasyUiTree;
import com.zwl.commons.pojo.EgoResult;

import java.util.List;


public interface TbContentCategoryServie {
	/**
	 * 查询所有类目并转换为easyui tree的属性要求
	 * @return
	 */
	List<EasyUiTree> showCategory(long id);
	
	/**
	 * 类目新增
	 * @return
	 */
	EgoResult create(TbContentCategory cate);
	
	/**
	 * 类目重命名
	 * @param cate
	 * @return
	 */
	EgoResult update(TbContentCategory cate);
	/**
	 * 删除类目
	 * @param id
	 * @return
	 */
	EgoResult delete(TbContentCategory cate);
}
