package com.zwl.order.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.github.pagehelper.PageInfo;
import com.kmall.pojo.TbOrder;
import com.kmall.service.TbOrderDubboService;
import com.zwl.commons.pojo.EgoResult;
import com.zwl.commons.utils.CookieUtils;
import com.zwl.commons.utils.HttpClientUtil;
import com.zwl.commons.utils.IDUtils;
import com.zwl.commons.utils.JsonUtils;
import com.zwl.order.config.AlipayConfig;
import com.zwl.order.pojo.MyOrderParam;
import com.zwl.order.service.TbOrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OrderController {
	@Resource
	private TbOrderService tbOrderServiceImpl;

	@Value("${passport.url}")
	private String passprtUrl;
	@Reference(version = "1.0.0")
	private TbOrderDubboService tbOrderDubboService;
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

	@RequestMapping("order/my-orders")
	public String showOrder(HttpServletRequest request,Model model){
		EgoResult erResult = new EgoResult();
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String result = HttpClientUtil.doPost(passprtUrl+token);
		EgoResult er = JsonUtils.jsonToPojo(result, EgoResult.class);
		Map user= (LinkedHashMap)er.getData();
		List<TbOrder> tbOrders= tbOrderServiceImpl.selAllOrderByUseID(Long.parseLong(user.get("id").toString()));
		er.setData(tbOrders);
		er.setStatus(200);
		model.addAttribute("er",er);
		return "my-orders";
	}


	@RequestMapping("order/my-orders-page")
	public String showOrderByPage(HttpServletRequest request,Model model,@RequestParam(name = "pageNum", defaultValue = "1") int pageNum, @RequestParam(name = "pageSize", defaultValue = "5") int pageSize){
		EgoResult erResult = new EgoResult();
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String result = HttpClientUtil.doPost(passprtUrl+token);
		EgoResult er = JsonUtils.jsonToPojo(result, EgoResult.class);
		Map user= (LinkedHashMap)er.getData();
		PageInfo<TbOrder> pageInfo = tbOrderServiceImpl.selAllOrderByUseID(Long.parseLong(user.get("id").toString()), pageNum, pageSize);
		System.out.println("pageInfo-----:" + pageInfo);
		er.setData(pageInfo);
		er.setStatus(200);
		model.addAttribute("er",er);
		return "my-orders";
	}






	/**
	 * 创建订单
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("order/create.html")
	public String createOrder(MyOrderParam param, HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException {

		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		long l = IDUtils.genItemId();
		String out_trade_no = String.valueOf(l);
		//付款金额，必填
		String total_amount = param.getPayment();
		//订单名称，必填
		String subject = param.getOrderShipping().getOrderId();
		//商品描述，可空
		String body = param.getOrderItems().toString();

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
				+ "\"total_amount\":\"" + total_amount + "\","
				+ "\"subject\":\"" + subject + "\","
				+ "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
		//		+ "\"total_amount\":\""+ total_amount +"\","
		//		+ "\"subject\":\""+ subject +"\","
		//		+ "\"body\":\""+ body +"\","
		//		+ "\"timeout_express\":\"10m\","
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

		//请求
		String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=" + AlipayConfig.charset);
		response.getWriter().write(form);//直接将完整的表单html输出到页面
		response.getWriter().flush();
		response.getWriter().close();


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
