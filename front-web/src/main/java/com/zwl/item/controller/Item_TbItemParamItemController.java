package com.zwl.item.controller;

import com.zwl.item.service.Item_TbItemParamItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class Item_TbItemParamItemController {
	@Resource
	private Item_TbItemParamItemService tbItemParamItemServiceImpl;
	/**
	 * 显示规格参数
	 * @param id
	 * @return
	 */
	@RequestMapping(value="item/param/{id}.html",produces="text/html;charset=utf-8")
	@ResponseBody
	public String param(@PathVariable long id){
		return tbItemParamItemServiceImpl.showParam(id);
	}
}
