package com.zwl.find.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kmall.pojo.TbItem;
import com.kmall.pojo.TbItemCat;
import com.kmall.pojo.TbItemDesc;
import com.kmall.service.TbItemCatDubboService;
import com.kmall.service.TbItemDescDubboService;
import com.kmall.service.TbItemDubboService;
import com.zwl.commons.pojo.TbItemChild;
import com.zwl.find.service.TbItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TbItemServiceImpl implements TbItemService {

	@Reference(version = "1.0.0")
	private TbItemDubboService tbItemDubboService;

	@Reference(version = "1.0.0")
	private TbItemCatDubboService tbItemCatDubboServiceImpl;

	@Reference(version = "1.0.0")
	private TbItemDescDubboService tbItemDescDubboServiceImpl;
	
	@Resource
	private HttpSolrClient solrClient;
	
	
	@Override
	public void init() throws SolrServerException, IOException {
		// TODO Auto-generated method stub
		//查询所有正常的商品
		List<TbItem> listItem=tbItemDubboService.selAllByStatus((byte)1);
		
		//循环遍历商品
		for (TbItem item : listItem) {
			//商品对应的类目信息
			TbItemCat cat =tbItemCatDubboServiceImpl.selById(item.getCid());
			//商品对应的描述信息
			TbItemDesc desc =tbItemDescDubboServiceImpl.selByItemid(item.getId());
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", item.getId());
			doc.addField("item_title", item.getTitle());
			doc.addField("item_sell_point",item.getSellPoint());
			doc.addField("item_price",item.getPrice() );
			doc.addField("item_image", item.getImage());
			doc.addField("item_category_name",cat.getName());
			doc.addField("item_desc", desc.getItemDesc());
			solrClient.add(doc);
		}
		//提交事务
		solrClient.commit();
				
				
				
	}


	@Override
	public Map<String, Object> selByQuery(String query, int page, int rows) throws SolrServerException, IOException {
		// TODO Auto-generated method stub
		//利用solr进行查询 
		//建立solr查询对象
		SolrQuery params=new SolrQuery();
		//设置分页条件
		params.setStart(rows*(page-1));
		params.setRows(rows);
		
		//设置查询条件
		params.setQuery("item_keywords:"+query);
		
		//设置高亮
		params.setHighlight(true);
		//设置高亮的属性
		params.addHighlightField("item_title");
		//设置高亮前面的部分
		params.setHighlightSimplePre("<span style='color:red'>");
		//设置高亮后面的部分
		params.setHighlightSimplePost("</span>");
		
		//进行查询，并得到查询结果集包含头部等
		QueryResponse response = solrClient.query(params);
		
		//声明返回对象之一
		List<TbItemChild> listChild=new ArrayList<>();
		
		//未高亮内容,是一个数组
		SolrDocumentList listSolr = response.getResults();
		//高亮内容
		Map<String,Map<String,List<String>>> hh=response.getHighlighting();
		
		for (SolrDocument doc : listSolr) {
			TbItemChild child=new TbItemChild();
			
			child.setId(Long.parseLong(doc.getFieldValue("id").toString()));
			//接下来就是设置成，如果高亮部分有就设置成高亮部分的，如果没有就设置成普通的样子
			//代码解释：首先根据ID查询，这个在这个设置的属性item_Tittle中有没有查询条件使得高亮得部分
			//如果有就使用，没有就使用原来得
			List<String> list=hh.get(doc.getFieldValue("id")).get("item_title");
			if(list!=null&&list.size()>0){
				child.setTitle(list.get(0));		
			}else{
				child.setTitle(doc.getFieldValue("item_title").toString());
			}
			
				//设置价格
				child.setPrice((Long) doc.getFieldValue("item_price"));
				//设置图像
				Object image=doc.getFieldValue("item_image");
				//判断图像属性image是否有值，如果没有则新建一个String数组对象赋值，如果有，则将其转化成数组对象并赋值
				child.setImages(image==null||image.equals("")?new String[1]:image.toString().split(","));
			
				//设置买点
				child.setSellPoint(doc.getFieldValue("item_sell_point").toString());
				
				//添加到数组中去
				listChild.add(child);
				
		}
		
		//因为页面结果要求多个数组，所以采用map得方式来传递值
		//也可以将其封装到一个对象中去
		
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("itemList",listChild);
		resultMap.put("totalPages", listSolr.getNumFound()%rows==0?listSolr.getNumFound()/rows:listSolr.getNumFound()/rows+1);  
		return resultMap;
	}


	@Override
	public int add(Map<String, Object> map, String desc) throws SolrServerException, IOException {
		// TODO Auto-generated method stub
		
		
		//创建Solr输入对象
		SolrInputDocument doc=new SolrInputDocument();
		
		doc.setField("id", map.get("id"));
		
		doc.setField("item_title", map.get("title"));
		
		doc.setField("item_sell_point", map.get("sellPoint"));
		
		doc.setField("item_price", map.get("price"));
		
		doc.setField("item_image", map.get("image"));
		
		doc.setField("item_category_name", tbItemCatDubboServiceImpl.selById((Integer) map.get("cid")).getName());
		
		doc.setField("item_desc", desc);
		//建立solr返回结果对象，
		UpdateResponse response = solrClient.add(doc);
		//提交事务
		solrClient.commit();
		
		
		//不解1：在Solr中进行新增的时候，其中response的status数字为0，表示新增成功
		if (response.getStatus()==0) {
			return 1;
		}
		
		return 0;
	
	}

}
