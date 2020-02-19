package com.kmall.service;


import com.kmall.pojo.TbUser;

public interface TbUserDubboService {
	/**
	 * 根据用户名和密码查询登录
	 * @param user
	 * @return
	 */
	TbUser selByUser(TbUser user);

	/**
	 * 注册一个用户
	 * @param user
	 * @return
	 */
	 int registry(TbUser user);
}
