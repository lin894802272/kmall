package com.zwl.frontweb.controller;


import com.kmall.pojo.TbContent;
import com.zwl.commons.utils.JsonUtils;
import com.zwl.frontweb.service.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class TbContentController {
	@Resource
	private TbContentService tbContentServiceImpl;
	
	@RequestMapping("showBigPic")
	public String showBigPic(Model model){
		String json = tbContentServiceImpl.showBigPic();
		List<TbContent> tbContents = JsonUtils.jsonToList(json, TbContent.class);
		model.addAttribute("ad1", tbContents);
		return "index";
	}
}
