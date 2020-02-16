package com.zwl.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kmall.pojo.TbOrder;
import com.zwl.commons.pojo.EgoResult;
import com.zwl.order.pojo.MyOrderParam;
import com.zwl.order.service.TbOrderService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OrderController {
	@Resource
	private TbOrderService tbOrderServiceImpl;
	/**
	 * 显示确认页面
	 * @param model
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping("order/order-cart.html")
	public String showCartOrder(Model model,@RequestParam("id") List<Long> ids,HttpServletRequest request){
		model.addAttribute("cartList", tbOrderServiceImpl.showOrderCart(ids, request));
		return "order-cart";
	}
	/**
	 * 创建订单
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("order/create.html")
	public String createOrder(MyOrderParam param, HttpServletRequest request, Model model, HttpSession session){
		EgoResult er = tbOrderServiceImpl.create(param, request);
		List<TbOrder> list= (List<TbOrder>) er.getData();
		System.out.println("list:"+list.size());
		model.addAttribute("er",er);
		if(er.getStatus()==200){
			return "my-orders";
		}else{
			request.setAttribute("message", "订单创建失败");
			return "error/exception";
		}
	}
}
