package com.zwl.consumer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.kmall.pojo.TbItem;
import com.kmall.pojo.TbItemDesc;
import com.kmall.pojo.TbItemParamItem;
import com.kmall.redis.dao.JedisDao;
import com.kmall.service.TbItemDescDubboService;
import com.kmall.service.TbItemDubboService;
import com.zwl.commons.pojo.EasyUIDataGrid;
import com.zwl.commons.utils.HttpClientUtil;
import com.zwl.commons.utils.IDUtils;
import com.zwl.commons.utils.JsonUtils;
import com.zwl.consumer.service.TbItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;


@Service
public class TbItemServiceImpl implements TbItemService {
	@Reference(version = "1.0.0" ,timeout = 3000)
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference(version = "1.0.0",timeout = 3000)
	private TbItemDescDubboService tbItemDescDubboService;
	//@Value("${search.url}")
	private String url="http://localhost:8082/solr/add";
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.item.key}")
	private String itemKey;
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		return tbItemDubboServiceImpl.show(page, rows);
	}

	@Override
	public int update(String ids, byte status) {
		int index = 0;
		TbItem item = new TbItem();
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			item.setId(Long.parseLong(id));
			item.setStatus(status);
			index += tbItemDubboServiceImpl.updItemStatus(item);
			if(status==2||status==3){
				jedisDaoImpl.del(itemKey+id);
			}
		}
		if (index == idsStr.length) {
			return 1;
		}
		
		
		return 0;
	}

	@Override
	public int save(TbItem item, String desc,String itemParams) throws Exception {
		
		
		
		// 不考虑事务回滚
		// long id = IDUtils.genItemId();
		// item.setId(id);
		// Date date = new Date();
		// item.setCreated(date);
		// item.setUpdated(date);
		// item.setStatus((byte)1);
		// int index = tbItemDubboServiceImpl.insTbItem(item);
		// if(index>0){
		// TbItemDesc itemDesc = new TbItemDesc();
		// itemDesc.setItemDesc(desc);
		// itemDesc.setItemId(id);
		// itemDesc.setCreated(date);
		// itemDesc.setUpdated(date);
		// index+=tbItemDescDubboService.insDesc(itemDesc);
		// }
		// if(index==2){
		// return 1;
		// }
		// 调用dubbo中考虑事务回滚功能方法
		long id = IDUtils.genItemId();
		item.setId(id);
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		item.setStatus((byte) 1);

		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(id);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		
		
		TbItemParamItem paramItem = new TbItemParamItem();
		paramItem.setCreated(date);
		paramItem.setUpdated(date);
		paramItem.setItemId(id);
		paramItem.setParamData(itemParams);


		int index = 0;

		index = tbItemDubboServiceImpl.insTbItemDesc(item, itemDesc,paramItem);
		System.out.println("index:" + index);
		
		
		
		final TbItem itemFinal = item;
		final String descFinal = desc;
		new Thread(){
			public void run() {
				Map<String,Object> map = new HashMap<>();
				map.put("item", itemFinal);
				map.put("desc", descFinal);


				HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
				//使用java代码调用其他项目的控制器
			};
		}.start();
		
		
		
		
		return index;
	}

}
