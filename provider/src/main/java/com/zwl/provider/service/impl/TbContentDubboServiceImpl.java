package com.zwl.provider.service.impl;

import java.util.List;

import javax.annotation.Resource;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kmall.pojo.TbContent;
import com.kmall.pojo.TbContentExample;
import com.kmall.service.StudentService;
import com.kmall.service.TbContentDubboService;
import com.zwl.commons.pojo.EasyUIDataGrid;
import com.zwl.provider.mapper.TbContentMapper;
@Service(timeout =4000, version = "1.0.0",interfaceClass = TbContentDubboService.class)   //dubbo service注解，需要指定版本,并告述它要实现那个接口,然后消费者消费的时候，需要指定版本号
@org.springframework.stereotype.Service
public class TbContentDubboServiceImpl implements TbContentDubboService {
	@Resource
	private TbContentMapper tbContentMapper;
	@Override
	public EasyUIDataGrid selContentByPage(long categoryId, int page, int rows) {
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		if(categoryId!=0){
			example.createCriteria().andCategoryIdEqualTo(categoryId);
		}
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		
		PageInfo<TbContent> pi = new PageInfo<>(list);
		
		EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		return datagrid;
	}
	@Override
	public int insContent(TbContent content) {
		return tbContentMapper.insertSelective(content);
	}
	@Override
	public List<TbContent> selByCount(int count, boolean isSort) {
		TbContentExample example = new TbContentExample();
		//排序
		if(isSort){
			example.setOrderByClause("updated desc");
		}
		if(count!=0){
			PageHelper.startPage(1, count);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			PageInfo<TbContent> pi = new PageInfo<>(list);
			return pi.getList();
		}else{
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
	}

}
