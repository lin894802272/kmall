package com.zwl.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kmall.redis.dao.JedisDao;
import com.kmall.service.TbItemDescDubboService;
import com.zwl.item.service.Item_TbItemDescService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class Item_TbItemDescServiceImpl implements Item_TbItemDescService {
	@Reference(version = "1.0.0")
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.desc.key}")
	private String descKey;
	@Override
	public String showDesc(long itemId) {
		String key = descKey + itemId;
		if(jedisDaoImpl.exists(key)){
			String json = jedisDaoImpl.get(key);
			if(json!=null&&!json.equals("")){
				return json;
			}
		}
		String result = tbItemDescDubboServiceImpl.selByItemid(itemId).getItemDesc();
		jedisDaoImpl.set(key, result);
		return result;
	}

}
