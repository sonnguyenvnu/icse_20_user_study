package com.roncoo.pay.permission.shiro.filter;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RcCaptchaValidateFilter extends AccessControlFilter {

	private boolean captchaEbabled = true;// æ˜¯å?¦å¼€å?¯éªŒè¯?ç ?æ”¯æŒ?

	private String captchaParam = "captchaCode";// å‰?å?°æ??äº¤çš„éªŒè¯?ç ?å?‚æ•°å??

	private String failureKeyAttribute = "shiroLoginFailure"; // éªŒè¯?ç ?éªŒè¯?å¤±è´¥å?Žå­˜å‚¨åˆ°çš„å±žæ€§å??

	public void setCaptchaEbabled(boolean captchaEbabled) {
		this.captchaEbabled = captchaEbabled;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	public void setFailureKeyAttribute(String failureKeyAttribute) {
		this.failureKeyAttribute = failureKeyAttribute;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		// 1ã€?è®¾ç½®éªŒè¯?ç ?æ˜¯å?¦å¼€å?¯å±žæ€§ï¼Œé¡µé?¢å?¯ä»¥æ ¹æ?®è¯¥å±žæ€§æ?¥å†³å®šæ˜¯å?¦æ˜¾ç¤ºéªŒè¯?ç ?
		request.setAttribute("captchaEbabled", captchaEbabled);

		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		// 2ã€?åˆ¤æ–­éªŒè¯?ç ?æ˜¯å?¦ç¦?ç”¨ æˆ–ä¸?æ˜¯è¡¨å?•æ??äº¤ï¼ˆå…?è®¸è®¿é—®ï¼‰
		if (captchaEbabled == false || !"post".equalsIgnoreCase(httpServletRequest.getMethod())) {
			return true;
		}
		// 3ã€?æ­¤æ—¶æ˜¯è¡¨å?•æ??äº¤ï¼ŒéªŒè¯?éªŒè¯?ç ?æ˜¯å?¦æ­£ç¡®
		// èŽ·å?–é¡µé?¢æ??äº¤çš„éªŒè¯?ç ?
		String submitCaptcha = httpServletRequest.getParameter(captchaParam);
		// èŽ·å?–sessionä¸­çš„éªŒè¯?ç ?
		String captcha = (String) httpServletRequest.getSession().getAttribute("rcCaptcha");
		if (submitCaptcha.equals(captcha)) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// å¦‚æžœéªŒè¯?ç ?å¤±è´¥äº†ï¼Œå­˜å‚¨å¤±è´¥keyå±žæ€§
		request.setAttribute(failureKeyAttribute, "éªŒè¯?ç ?é”™è¯¯!");
		return true;
	}

}
