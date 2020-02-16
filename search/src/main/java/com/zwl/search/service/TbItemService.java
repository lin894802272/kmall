package com.zwl.search.service;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

public interface TbItemService {

	/**
	 * 初始化数据
	 * @throws SolrServerException
	 * @throws IOException
	 */
	void init() throws SolrServerException, IOException;

	/**
	 * 进行分页查询，根据前台发送过来的请求来设置
	 * @param query
	 * @param page
	 * @param rows
	 * @return
	 */
	Map<String,Object> selByQuery(String query, int page, int rows)throws SolrServerException, IOException;
	

	/**
	 * 新增一个商品的solr到solr服务器中去，
	 * 为什么：为什么还需要添加的dscc的参数过来，因为那边的sql插入还没有提交事务呢，你这边怎么通过
	 * 商品的id查到对应的描述呢，所以需要把对应的desc传递过来，但是如果你在执行之前就完成插入，就可以不用传了
	 * @param map
	 * @param desc
	 * @return
	 */
	int add(Map<String, Object> map, String desc)throws SolrServerException, IOException ;


	
	
	
	
}
