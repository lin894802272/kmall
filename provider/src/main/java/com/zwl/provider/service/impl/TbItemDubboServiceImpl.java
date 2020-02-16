package com.zwl.provider.service.impl;

import java.util.List;

import javax.annotation.Resource;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kmall.pojo.TbItem;
import com.kmall.pojo.TbItemDesc;
import com.kmall.pojo.TbItemExample;
import com.kmall.pojo.TbItemParamItem;
import com.kmall.service.StudentService;
import com.kmall.service.TbItemDubboService;
import com.zwl.commons.pojo.EasyUIDataGrid;
import com.zwl.provider.mapper.TbItemDescMapper;
import com.zwl.provider.mapper.TbItemMapper;
import com.zwl.provider.mapper.TbItemParamItemMapper;
@Service(timeout =4000, version = "1.0.0",interfaceClass = TbItemDubboService.class)   //dubbo service注解，需要指定版本,并告述它要实现那个接口,然后消费者消费的时候，需要指定版本号
@org.springframework.stereotype.Service
public class TbItemDubboServiceImpl implements TbItemDubboService {
	@Resource
	private TbItemMapper tbItemMapper;
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		//System.out.println("hello word");
		//设置分页从第几行开始,一页有几行
		System.out.println("page:"+page);
		System.out.println("rows:"+rows);
		PageHelper.startPage(page, rows);

		//查询全部商品
		List<TbItem> list=tbItemMapper.selectByExample(new TbItemExample());

		//System.out.println(list.toString());

		//分页代码
		//设置分页条件
		PageInfo<TbItem> pi =new PageInfo<>(list);

		//放入到实体类
		EasyUIDataGrid datagrid=new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		return datagrid;
	}
	@Override
	public int updItemStatus(TbItem tbItem) {
		
		
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}
	@Override
	public int insTbItem(TbItem tbItem) {
		return tbItemMapper.insert(tbItem);
	}
	@Override
	public int insTbItemDesc(TbItem tbItem, TbItemDesc desc, TbItemParamItem paramItem) throws Exception {
		int index =0;
		try {
			index= tbItemMapper.insertSelective(tbItem);
			index+= tbItemDescMapper.insertSelective(desc);
			index+=tbItemParamItemMapper.insertSelective(paramItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(index==3){
			return 1;
		}else{
			throw new Exception("新增失败,数据还原");
		}
	}
	@Override
	public List<TbItem> selAllByStatus(byte status) {
		TbItemExample example = new TbItemExample();
		example.createCriteria().andStatusEqualTo(status);
		return tbItemMapper.selectByExample(example);
	}
	@Override
	public TbItem selById(long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}
}
