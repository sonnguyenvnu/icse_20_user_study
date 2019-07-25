/**
 * Copyright (c) 2015-2017, Javen Zhou  (javen205@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jpay.unionpay;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.jpay.ext.kit.HttpKit;
import com.jpay.util.HttpUtils;

/**
 * @author Javen
 */
public class UnionPayApi {
	/**
	 * PC网关支付�?WAP支付
	 * 
	 * @param resp
	 *            HttpServletResponse
	 * @param reqData
	 *            请求�?�数
	 * @throws IOException
	 */
	@Deprecated
	public static void frontConsume(HttpServletResponse resp, Map<String, String> reqData) throws IOException {
		String html = AcpService.createAutoFormHtml(SDKConfig.getConfig().getFrontRequestUrl(), reqData, "UTF-8");
		resp.getWriter().write(html);
	}
	/**
	 * �?端请求
	 * 
	 * @param resp
	 *            HttpServletResponse
	 * @param reqData
	 *            请求�?�数
	 * @throws IOException
	 */
	public static void frontRequest(HttpServletResponse resp, Map<String, String> reqData) throws IOException {
		String html = AcpService.createAutoFormHtml(SDKConfig.getConfig().getFrontRequestUrl(), reqData, "UTF-8");
		resp.getWriter().write(html);
	}

	/**
	 * 退货交易�?撤销交易
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {String}
	 */
	@Deprecated
	public static String refund(Map<String, String> reqData) {
		return HttpUtils.post(SDKConfig.getConfig().getBackRequestUrl(), reqData);
	}

	/**
	 * 退货交�?撤销交易
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {Map<String, String>}
	 */
	@Deprecated
	public static Map<String, String> refundByMap(Map<String, String> reqData) {
		return SDKUtil.convertResultStringToMap(refund(reqData));
	}
	
	
	/**
	 * �?��?�请求返回String
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {String}
	 */
	public static String backRequest(Map<String, String> reqData) {
		return HttpUtils.post(SDKConfig.getConfig().getBackRequestUrl(), reqData);
	}

	/**
	 * �?��?�请求返回Map
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {Map<String, String>}
	 */
	public static Map<String, String> backRequestByMap(Map<String, String> reqData) {
		return SDKUtil.convertResultStringToMap(backRequest(reqData));
	}


	/**
	 * �?�订�?�查询返回String
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {String}
	 */
	public static String singleQuery(Map<String, String> reqData) {
		return HttpUtils.post(SDKConfig.getConfig().getSingleQueryUrl(), reqData);
	}

	/**
	 * �?�订�?�查询返回Map
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {Map<String, String>}
	 */
	public static Map<String, String> singleQueryByMap(Map<String, String> reqData) {
		return SDKUtil.convertResultStringToMap(singleQuery(reqData));
	}

	/**
	 * 文件传输类接�?�
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {String}
	 */
	public static String fileTransfer(Map<String, String> reqData) {
		return HttpUtils.post(SDKConfig.getConfig().getFileTransUrl(), reqData);
	}

	/**
	 * 文件传输类接�?�
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {Map<String, String>}
	 */
	public static Map<String, String> fileTransferByMap(Map<String, String> reqData) {
		return SDKUtil.convertResultStringToMap(fileTransfer(reqData));
	}

	/**
	 * APP控件支付
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {String}
	 */
	public static String AppConsume(Map<String, String> reqData) {
		return HttpUtils.post(SDKConfig.getConfig().getAppRequestUrl(), reqData);
	}

	/**
	 * APP控件支付
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {Map<String, String>}
	 */
	public static Map<String, String> AppConsumeByMap(Map<String, String> reqData) {
		return SDKUtil.convertResultStringToMap(AppConsume(reqData));
	}

	/**
	 * 网关缴费
	 * 
	 * @param resp
	 *            HttpServletResponse
	 * @param reqData
	 *            请求�?�数
	 * @throws IOException
	 */
	public static void jfFrontConsume(HttpServletResponse resp, Map<String, String> reqData) throws IOException {
		String html = AcpService.createAutoFormHtml(SDKConfig.getConfig().getJfFrontRequestUrl(), reqData, "UTF-8");
		resp.getWriter().write(html);
	}

	/**
	 * APP缴费
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return {String}
	 */
	public static String jfAppTrans(Map<String, String> reqData) {
		return HttpUtils.post(SDKConfig.getConfig().getAppRequestUrl(), reqData);
	}

	/**
	 * APP缴费
	 * 
	 * @param reqData
	 *            请求�?�数
	 * @return Map<String, String>
	 */
	public static Map<String, String> jfAppTransByMap(Map<String, String> reqData) {
		return SDKUtil.convertResultStringToMap(jfAppTrans(reqData));
	}

	/**
	 * 获�?�地区列表
	 * 
	 * @return {String}
	 */
	public static String getAllAreas() {
		return HttpKit.get("https://gateway.95516.com/jiaofei/config/s/areas");
	}

	/**
	 * 获�?�业务目录
	 * 
	 * @return {String}
	 */
	public static String getAllCategories() {
		return HttpKit.get("https://gateway.95516.com/jiaofei/config/s/categories/00");
	}
}
