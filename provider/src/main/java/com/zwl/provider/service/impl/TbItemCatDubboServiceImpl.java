package com.zwl.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kmall.pojo.TbItemCat;
import com.kmall.pojo.TbItemCatExample;
import com.kmall.service.StudentService;
import com.kmall.service.TbItemCatDubboService;
import com.zwl.provider.mapper.TbItemCatMapper;

import java.util.List;

import javax.annotation.Resource;


@Service(timeout =4000, version = "1.0.0",interfaceClass = TbItemCatDubboService.class)   //dubbo service注解，需要指定版本,并告述它要实现那个接口,然后消费者消费的时候，需要指定版本号
@org.springframework.stereotype.Service
public class TbItemCatDubboServiceImpl implements TbItemCatDubboService {
	@Resource
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<TbItemCat> show(long pid) {
		TbItemCatExample example =new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(pid);
		return tbItemCatMapper.selectByExample(example);
	}
	@Override
	public TbItemCat selById(long id) {
		return tbItemCatMapper.selectByPrimaryKey(id);
	}

}
