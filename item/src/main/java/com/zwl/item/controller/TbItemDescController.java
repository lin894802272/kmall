package com.zwl.item.controller;

import javax.annotation.Resource;

import com.zwl.item.service.TbItemDescService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class TbItemDescController {
	@Resource
	private TbItemDescService tbItemDescServiceImpl;
	/**
	 * 显示商品详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value="item/desc/{id}.html",produces="text/html;charset=utf-8")
	@ResponseBody
	public String desc(@PathVariable long id){
		return tbItemDescServiceImpl.showDesc(id);
	}
}
