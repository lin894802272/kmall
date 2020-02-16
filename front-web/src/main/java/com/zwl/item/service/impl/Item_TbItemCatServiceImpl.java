package com.zwl.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kmall.pojo.TbItemCat;
import com.kmall.service.TbItemCatDubboService;
import com.zwl.item.pojo.PortalMenu;
import com.zwl.item.pojo.PortalMenuNode;
import com.zwl.item.service.Item_TbItemCatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class Item_TbItemCatServiceImpl implements Item_TbItemCatService {
	@Reference(version = "1.0.0")
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Override
	public PortalMenu showCatMenu() {
		//查询出所有一级菜单
		List<TbItemCat> list = tbItemCatDubboServiceImpl.show(0);
		PortalMenu pm =new PortalMenu();
		pm.setData(selAllMenu(list));
		return pm;
	}
	/**
	 * 最终返回结果所有查询到的结果.
	 */
	public List<Object> selAllMenu(List<TbItemCat> list){
		List<Object> listNode = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			if(tbItemCat.getIsParent()){
				PortalMenuNode pmd  = new PortalMenuNode();
				pmd.setU("/products/"+tbItemCat.getId()+".html");
				pmd.setN("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				pmd.setI(selAllMenu(tbItemCatDubboServiceImpl.show(tbItemCat.getId())));
				listNode.add(pmd);
			}else{
				listNode.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		
		return listNode;
	}
}
