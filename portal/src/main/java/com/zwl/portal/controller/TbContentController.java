package com.zwl.portal.controller;

import javax.annotation.Resource;

import com.zwl.portal.service.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class TbContentController {
	@Resource
	private TbContentService tbContentServiceImpl;
	
	@RequestMapping("showBigPic")
	public String showBigPic(Model model){
		model.addAttribute("ad1", tbContentServiceImpl.showBigPic());
		return "index";
	}
}
