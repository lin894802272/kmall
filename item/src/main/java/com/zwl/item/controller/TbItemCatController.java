
package com.zwl.item.controller;
import javax.annotation.Resource;

import com.zwl.item.service.TbItemCatService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TbItemCatController {
	@Resource
	private TbItemCatService tbItemCatServiceImpl;
	/**
	 * 返回jsonp数据格式包含所有菜单信息
	 * @param callback
	 * @return
	 */
	@RequestMapping("rest/itemcat/all")
	@ResponseBody
	public MappingJacksonValue showMenu(String callback){
		MappingJacksonValue mjv = new MappingJacksonValue(tbItemCatServiceImpl.showCatMenu());
		//mjv.setJsonpFunction(callback);
		return mjv;
	}
}
