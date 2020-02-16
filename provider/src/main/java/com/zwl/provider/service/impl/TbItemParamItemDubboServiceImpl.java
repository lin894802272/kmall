package com.zwl.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kmall.pojo.TbItemParamItem;
import com.kmall.pojo.TbItemParamItemExample;
import com.kmall.service.StudentService;
import com.kmall.service.TbItemParamItemDubboSerice;
import com.zwl.provider.mapper.TbItemParamItemMapper;

import java.util.List;

import javax.annotation.Resource;


@Service(timeout =4000, version = "1.0.0",interfaceClass = TbItemParamItemDubboSerice.class)   //dubbo service注解，需要指定版本,并告述它要实现那个接口,然后消费者消费的时候，需要指定版本号
@org.springframework.stereotype.Service
public class TbItemParamItemDubboServiceImpl  implements TbItemParamItemDubboSerice {
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public TbItemParamItem selByItemid(long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

}
