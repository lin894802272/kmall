package com.zwl.frontweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/")
	public String welcome(){
		return "forward:/showBigPic";
	}
}
