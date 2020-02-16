package com.zwl.passport.service;

import com.kmall.pojo.TbUser;
import com.zwl.commons.pojo.EgoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface TbUserService {
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	EgoResult login(TbUser user, HttpServletRequest request, HttpServletResponse response);
	/**
	 * 根据token查询用户信息
	 * @param token
	 * @return
	 */
	EgoResult getUserInfoByToken(String token);
	
	/**
	 * 退出
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 */
	EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response);
}
