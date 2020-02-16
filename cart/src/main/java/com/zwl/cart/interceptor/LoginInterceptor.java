package com.zwl.cart.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zwl.commons.pojo.EgoResult;
import com.zwl.commons.utils.CookieUtils;
import com.zwl.commons.utils.HttpClientUtil;
import com.zwl.commons.utils.JsonUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LoginInterceptor implements HandlerInterceptor {
	
	/**
	 * 这个拦截器的作用，当用户点击商品的加入购物车，我们进行拦截，
	 * 如果用户登录了，就是浏览器存在cookie了，那我们利用此cookie,去对应项目请求用户的个人数据，然后return true，放用户去访问加入购物车的控制器，并进入对应的页面
	 * 如果用户没有登录，我们就通过重定向到登录页面，为了实现登录成功后直接跳转到加入购物车页面，我们在参数上添加interurl的参数，让用户进行登录过后就可以直接跳转到购物车成功的页面
	 *而不必重新跳转到商品的详情页面s 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		//如果获取到的cookie不为空，那我们就用这个cookie去访问8082的控制器，获取用户的信息，并返回成功，不用跳转到登录页面
		if(token!=null&&!token.equals("")){
			String json = HttpClientUtil.doPost("http://localhost:8082/user/token/"+token);
			EgoResult er = JsonUtils.jsonToPojo(json, EgoResult.class);
			if(er.getStatus()==200){
				return true;
			}
		}
		//获取用户拦截url中num参数的值
		String num = request.getParameter("num");
		// %3F 表示转义字符 ? 
		response.sendRedirect("http://localhost:8082/user/showLogin?interurl="+request.getRequestURL()+"%3Fnum="+num);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
