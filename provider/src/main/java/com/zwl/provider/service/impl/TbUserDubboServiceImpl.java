package com.zwl.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kmall.pojo.TbUser;
import com.kmall.pojo.TbUserExample;
import com.kmall.service.StudentService;
import com.kmall.service.TbUserDubboService;
import com.zwl.provider.mapper.TbUserMapper;

import java.util.List;

import javax.annotation.Resource;

@Service(timeout =4000, version = "1.0.0",interfaceClass = TbUserDubboService.class)   //dubbo service注解，需要指定版本,并告述它要实现那个接口,然后消费者消费的时候，需要指定版本号
@org.springframework.stereotype.Service
public class TbUserDubboServiceImpl implements TbUserDubboService {
	@Resource
	private TbUserMapper tbUserMapper;
	@Override
	public TbUser selByUser(TbUser user) {
		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
