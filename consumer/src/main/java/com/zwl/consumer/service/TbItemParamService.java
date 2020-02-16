package com.zwl.consumer.service;


import com.kmall.pojo.TbItemParam;
import com.zwl.commons.pojo.EasyUIDataGrid;
import com.zwl.commons.pojo.EgoResult;

public interface TbItemParamService {
	/**
	 * 分页显示商品规格参数
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showPage(int page, int rows);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int delete(String ids) throws Exception;
	/**
	 * 根据类目id查询模板信息
	 * @param catId
	 * @return
	 */
	EgoResult showParam(long catId);
	/**
	 * 新增模板信息
	 * @param param
	 * @return
	 */
	EgoResult save(TbItemParam param);
}
