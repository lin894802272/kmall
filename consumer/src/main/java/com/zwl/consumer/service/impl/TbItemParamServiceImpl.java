package com.zwl.consumer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kmall.pojo.TbItemParam;
import com.kmall.service.TbItemCatDubboService;
import com.kmall.service.TbItemParamDubboService;
import com.zwl.commons.pojo.EasyUIDataGrid;
import com.zwl.commons.pojo.EgoResult;
import com.zwl.consumer.pojo.TbItemParamChild;
import com.zwl.consumer.service.TbItemParamService;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {
	@Reference(version = "1.0.0")
	private TbItemParamDubboService tbItemParamDubboServiceImpl;
	@Reference(version = "1.0.0")
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		EasyUIDataGrid datagrid = tbItemParamDubboServiceImpl.showPage(page, rows);
		List<TbItemParam> list = (List<TbItemParam>) datagrid.getRows();
		List<TbItemParamChild> listChild = new ArrayList<>();
		for (TbItemParam param : list) {
			TbItemParamChild child = new TbItemParamChild();
			
			child.setCreated(param.getCreated());
			child.setId(param.getId());
			child.setItemCatId(param.getItemCatId());
			child.setParamData(param.getParamData());
			child.setUpdated(param.getUpdated());
			child.setItemCatName(tbItemCatDubboServiceImpl.selById(child.getItemCatId()).getName());
			
			
			listChild.add(child);
		}
		datagrid.setRows(listChild);
		return datagrid;
	}
	@Override
	public int delete(String ids) throws Exception {
		return tbItemParamDubboServiceImpl.delByIds(ids);
	}
	@Override
	public EgoResult showParam(long catId) {
		EgoResult er = new EgoResult();
		TbItemParam param = tbItemParamDubboServiceImpl.selByCatid(catId);
		if(param!=null){
			er.setStatus(200);
			er.setData(param);
		}
		return er;
	}
	@Override
	public EgoResult save(TbItemParam param) {
		Date date = new Date();
		param.setCreated(date);
		param.setUpdated(date);
		int index = tbItemParamDubboServiceImpl.insParam(param);
		EgoResult er = new EgoResult();
		if(index>0){
			er.setStatus(200);
		}
		return er;
	}

}
