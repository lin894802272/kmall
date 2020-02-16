package com.zwl.consumer.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.zwl.commons.utils.FtpUtil;
import com.zwl.commons.utils.IDUtils;
import com.zwl.consumer.service.PicService;
import com.zwl.consumer.util.QiniuUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;




@Service
public class PicServiceImpl implements PicService {

	//@Value("${ftpclient.host}")
	private String host="122.112.175.38";
	//@Value("${ftpclient.port}")
	private int port=21;
	//@Value("${ftpclient.username}")
	private String username="ftpuser";
	//@Value("${ftpclient.password}")
	private String password="123456";
	//@Value("${ftpclient.basepath}")
	private String basePath="/home/ftpuser";
	//@Value("${ftpclient.filepath}")
	private String filePath="/";


	@Override
	public Map<String,Object> upload(MultipartFile file) throws IOException {
		String genImageName = IDUtils.genImageName()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		boolean result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, genImageName, file.getInputStream());
		Map<String,Object> map = new HashMap<>();
		System.out.println("result:"+result);
		if(result){
			map.put("error", 0);
			map.put("url","http://"+ host+"/"+genImageName);
		}else{
			map.put("error", 1);
			map.put("message", "图片上传失败");
		}
		return map;
	}
}