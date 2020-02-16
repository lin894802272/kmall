package com.zwl.item.controller;

import com.zwl.item.service.Item_TbItemDescService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class Item_TbItemDescController {
	@Resource
	private Item_TbItemDescService tbItemDescServiceImpl;
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
