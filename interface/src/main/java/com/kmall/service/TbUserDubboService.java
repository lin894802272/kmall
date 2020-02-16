package com.kmall.service;


import com.kmall.pojo.TbUser;

public interface TbUserDubboService {
	/**
	 * 根据用户名和密码查询登录
	 * @param user
	 * @return
	 */
	TbUser selByUser(TbUser user);
}
