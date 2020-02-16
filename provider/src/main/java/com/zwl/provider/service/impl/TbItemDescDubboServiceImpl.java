package com.zwl.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kmall.pojo.TbItemDesc;
import com.kmall.service.StudentService;
import com.kmall.service.TbItemDescDubboService;
import com.zwl.provider.mapper.TbItemDescMapper;

import javax.annotation.Resource;


@Service(timeout=4000, version = "1.0.0",interfaceClass = TbItemDescDubboService.class)   //dubbo service注解，需要指定版本,并告述它要实现那个接口,然后消费者消费的时候，需要指定版本号
@org.springframework.stereotype.Service
public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public int insDesc(TbItemDesc itemDesc) {
		return tbItemDescMapper.insert(itemDesc);
	}
	@Override
	public TbItemDesc selByItemid(long itemid) {
		return tbItemDescMapper.selectByPrimaryKey(itemid);
	}

}
