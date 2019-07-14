package com.springboot.util;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {

	/**
	 * 获�?�IP地�?�
	 * 
	 * 使用Nginx等�??�?�代�?�软件， 则�?能通过request.getRemoteAddr()获�?�IP地�?�
	 * 如果使用了多级�??�?�代�?�的�?，X-Forwarded-For的值并�?止一个，而是一串IP地�?�，X-Forwarded-For中第一个�?�unknown的有效IP字符串，则为真实IP地�?�
	 */
	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}

}
