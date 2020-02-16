package com.zwl.consumer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.zwl.commons.utils.JsonUtils;
import com.zwl.consumer.service.PicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class PicController {
	@Resource
	private PicService picServiceImpl;
	
	/**
	 * 图片上传
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("pic/upload")
	@ResponseBody
	public String upload(MultipartFile uploadFile){

		System.out.println("我进到上传图片的控制器");
		Map<String,Object> map = new HashMap<>();
		try {
			map= picServiceImpl.upload(uploadFile);

		} catch (IOException e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message","上传图片时服务器异常");
		}
		String s = JsonUtils.objectToJson(map);
		System.out.println("map:"+map);
		return s;
	}
}
