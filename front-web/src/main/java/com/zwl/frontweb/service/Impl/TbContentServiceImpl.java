package com.zwl.frontweb.service.Impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kmall.pojo.TbContent;
import com.kmall.redis.dao.JedisDao;
import com.kmall.service.TbContentDubboService;
import com.zwl.commons.utils.JsonUtils;

import com.zwl.frontweb.service.TbContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TbContentServiceImpl implements TbContentService {
	@Reference(version = "1.0.0")
	private TbContentDubboService tbContentDubboServiceImpl;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.bigpic.key}")
	private String key;
	@Override
	public String showBigPic() {
		//先判断在redis中是否存在
		if(jedisDaoImpl.exists(key)){
			String value = jedisDaoImpl.get(key);
			if(value!=null&&!value.equals("")){
				return value;
			}
		}
		
		//如果不存在从mysql中取,取完后放入redis中
		List<TbContent> list = tbContentDubboServiceImpl.selByCount(3, true);
		
		/*List<Map<String,Object>> listResult = new ArrayList<>();
		for (TbContent tc : list) {
			Map<String,Object> map = new HashMap<>();
			
			map.put("srcB", tc.getPic2());
			map.put("height", 240);
			map.put("alt", "对不起,加载图片失败");
			map.put("width", 670);
			map.put("src", tc.getPic());
			map.put("widthB", 550);
			map.put("href", tc.getUrl() );
			map.put("heightB", 240);
			
			listResult.add(map);
		}*/
		String listJson = JsonUtils.objectToJson(list);
		//把数据放入到redis中
		jedisDaoImpl.set(key, listJson);
		return listJson;
	}

}
