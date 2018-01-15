package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

public abstract class BaseController {
	protected Integer getUidFromSession(HttpSession session) {
		return (Integer)session.getAttribute("uid");
	}
	protected String getUsernameFromSession(HttpSession session) {
		return (String)session.getAttribute("username");
	}
	protected String getPhoneFromSession(HttpSession session) {
		return (String)session.getAttribute("phone");
	}
}
