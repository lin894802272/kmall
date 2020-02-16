package com.zwl.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kmall.pojo.TbItem;
import com.kmall.redis.dao.JedisDao;
import com.kmall.service.TbItemDubboService;
import com.zwl.commons.pojo.TbItemChild;
import com.zwl.commons.utils.JsonUtils;
import com.zwl.item.service.TbItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class TbItemServiceImpl implements TbItemService {
	@Reference(version = "1.0.0")
	private TbItemDubboService tbItemDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.item.key}")
	private String itemKey;
	@Override
	public TbItemChild show(long id) {
		String key = itemKey+id;
		if(jedisDaoImpl.exists(key)){
			String json = jedisDaoImpl.get(key);
			if(json!=null&&!json.equals("")){
				return JsonUtils.jsonToPojo(json, TbItemChild.class);
			}
		}
		TbItem item = tbItemDubboServiceImpl.selById(id);
		TbItemChild child = new TbItemChild();
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setPrice(item.getPrice());
		child.setSellPoint(item.getSellPoint());
		child.setImages(item.getImage()!=null&&!item.equals("")?item.getImage().split(","):new String[1]);
		//存到数据库中
		jedisDaoImpl.set(key, JsonUtils.objectToJson(child));
		return child;
	}

}
