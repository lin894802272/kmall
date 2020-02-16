package com.zwl.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kmall.pojo.TbContentCategory;
import com.kmall.pojo.TbContentCategoryExample;
import com.kmall.service.StudentService;
import com.kmall.service.TbContentCategoryDubboService;
import com.zwl.provider.mapper.TbContentCategoryMapper;

import java.util.List;

import javax.annotation.Resource;


@Service(timeout =4000, version = "1.0.0",interfaceClass =TbContentCategoryDubboService.class)   //dubbo service注解，需要指定版本,并告述它要实现那个接口,然后消费者消费的时候，需要指定版本号
@org.springframework.stereotype.Service
public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {
	@Resource
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<TbContentCategory> selByPid(long id) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
		return tbContentCategoryMapper.selectByExample(example);
	}

	@Override
	public int insTbContentCategory(TbContentCategory cate) {
		return tbContentCategoryMapper.insertSelective(cate);
	}

	@Override
	public int updIsParentById(TbContentCategory cate) {
		return tbContentCategoryMapper.updateByPrimaryKeySelective(cate);
	}

	@Override
	public TbContentCategory selById(long id) {
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}
}
