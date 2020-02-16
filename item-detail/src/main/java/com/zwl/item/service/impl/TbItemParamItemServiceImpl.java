package com.zwl.item.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kmall.pojo.TbItemParamItem;
import com.kmall.service.TbItemParamItemDubboSerice;
import com.zwl.commons.utils.JsonUtils;
import com.zwl.item.pojo.ParamItem;
import com.zwl.item.service.TbItemParamItemService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {
	@Reference(version = "1.0.0")
	private TbItemParamItemDubboSerice tbItemParamItemDubboSericeImpl;
	@Override
	public String showParam(long itemId) {
		System.out.println("itemid:"+itemId);
		TbItemParamItem item = tbItemParamItemDubboSericeImpl.selByItemid(itemId);
		System.out.println(item==null);
			List<ParamItem> list = JsonUtils.jsonToList(item.getParamData(), ParamItem.class);
		System.out.println(list);
		StringBuffer sf = new StringBuffer();
		
		for (ParamItem param : list) {
			sf.append("<table width='500' style='color:gray;'>");
			for (int i = 0 ;i<param.getParams().size();i++) {
				if(i==0){
					sf.append("<tr>");
					sf.append("<td align='right' width='30%'>"+param.getGroup()+"</td>");
					sf.append("<td align='right' width='30%'>"+param.getParams().get(i).getK()+"</td>");
					sf.append("<td>"+param.getParams().get(i).getV()+"</td>");
					sf.append("<tr/>");
				}else{
					sf.append("<tr>");
					sf.append("<td> </td>");
					sf.append("<td align='right'>"+param.getParams().get(i).getK()+"</td>");
					sf.append("<td>"+param.getParams().get(i).getV()+"</td>");
					sf.append("</tr>");
				}
			}
			sf.append("</table>");
			sf.append("<hr style='color:gray;'/>");
		}
		return sf.toString();
	}

}
