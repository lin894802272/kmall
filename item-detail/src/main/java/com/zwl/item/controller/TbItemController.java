package com.zwl.item.controller;

import com.zwl.item.service.TbItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;


@Controller
public class TbItemController {
	@Resource
	private TbItemService tbItemSerivceImpl;
	
	/**
	 * 显示商品详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("item/{id}.html")
	public String showItemDetails(@PathVariable long id,Model model){
		model.addAttribute("item", tbItemSerivceImpl.show(id));
		return "item";
	}
}
