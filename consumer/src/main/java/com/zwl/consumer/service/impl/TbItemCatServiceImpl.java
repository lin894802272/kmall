package com.zwl.consumer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.kmall.pojo.TbItemCat;
import com.kmall.service.TbItemCatDubboService;
import com.zwl.commons.pojo.EasyUiTree;
import com.zwl.consumer.service.TbItemCatService;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;


@Service
public class TbItemCatServiceImpl implements TbItemCatService {
	@Reference(version = "1.0.0")
	private TbItemCatDubboService tbItemCatDubboServiceImpl;
	@Override
	public List<EasyUiTree> show(long pid) {
		List<TbItemCat> list = tbItemCatDubboServiceImpl.show(pid);
		List<EasyUiTree> listTree = new ArrayList<>();
		for (TbItemCat cat : list) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(cat.getId());
			tree.setText(cat.getName());
			tree.setState(cat.getIsParent()?"closed":"open");
			listTree.add(tree);
		}
		return listTree;
	}
}
