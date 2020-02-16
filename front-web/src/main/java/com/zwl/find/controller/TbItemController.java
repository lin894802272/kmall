package com.zwl.find.controller;


import com.zwl.find.service.TbItemService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


@Controller
public class TbItemController {

	@Resource
	private TbItemService tbItemServiceImpl;
	
	/*@RequestMapping("{page}")
	public String showPage(@PathVariable String page){
		return page;
	}*/
	
	
	/**
	 * 
	 * produces：设置返回的数据的类型以及编码
	 * 初始化
	 */
	@RequestMapping(value="solr/init",produces="text/html;charset=utf-8")
	@ResponseBody
	public String init(){
		
		long start=System.currentTimeMillis();
		
		try {
			tbItemServiceImpl.init();
			long end =System.currentTimeMillis();
			return "初始化总时间："+(end-start)/1000;
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "初始化失败";
		}	
	}
	
	/**
	 * 提供给前台项目得搜索功能
	 * @param model
	 * @param q
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("search.html")
	public String search(Model model,String q,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="12")int rows){
		
		//首先因为请求时通过get方式提交得，那个拦截器无法生效，没有转化成utf-8的格式，所以我们需要手动转化
		try {
			//q=new String(q.getBytes("iso-8859-1"),"utf-8");
			//调用服务层的方法，获取前台需要的数据
			Map<String,Object> map=tbItemServiceImpl.selByQuery(q, page, rows);
			model.addAttribute("query", q);
			model.addAttribute("itemList", map.get("itemList"));
			model.addAttribute("totalPages",map.get("totalPages"));
			model.addAttribute("page", page);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//跳转到search搜索页面
		return "search";	
	}
	
	
	
	
	
	/**
	 * 这个方法解释：这是一个solr同步的方法，在manage项目新增项目的时候，
	 * 我们需要同步新增的商品内容到solr中去，好让在前台项目进行查询的时候，有对应的项目
	 * 所以这个控制器的方法，是一个提供manage跨域请求的方法，
	 * 然后在manage进行请求的时候，是采用httpclient的技术，具体怎么使用参照使用手册
	 * 然后这个技术传过来的参数是封装成一个map中，并是一个流的形式，所以我们接受参数的时候，
	 * 需要在参数的前面加上RequestBody的注解，表明接受的参数是一个流形式（Json）
	 *
	 * @RequestBody ：把亲求体中流数据转化成指定数据类型
	 * 
	 * 参数
	 * req.getParameter() 获取参数
	 * 
	 * @RequestBody+参数
	 * req.getInputStream() 获取请求体中的流数据
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("solr/add")
	@ResponseBody
	public int add(@RequestBody Map<String,Object> map){
		System.out.println(map);
		System.out.println(map.get("item"));

		try {
			//调用增加solr查询内容的方法，并把参数传过去
			return tbItemServiceImpl.add((LinkedHashMap)map.get("item"), map.get("desc").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
}
