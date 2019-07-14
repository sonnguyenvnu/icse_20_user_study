/*
 * Copyright 2015-2102 RonCoo(http://www.roncoo.com) Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.roncoo.pay.trade.utils.httpclient;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <b>功能说明:
 * </b>
 * @author  Peter
 * <a href="http://www.roncoo.com">龙果学院(www.roncoo.com)</a>
 */
public class SimpleHttpParam {
	
	/**
	 * 请求地�?�
	 */
	private String url;
	/**
	 * 请求�?�数
	 */
	private Map<String,Object> parameters = new LinkedHashMap<String,Object>();
	/**
	 * HTTP请求方�?(GET或者POST)
	 */
	private String method = SimpleHttpUtils.HTTP_METHOD_GET;
	/**
	 * 请求字符集
	 */
	private String charSet = SimpleHttpUtils.DEFAULT_CHARSET;
	/**
	 * 是�?�验�?�?务端�?书
	 */
	private boolean sslVerify = false;
	/**
	 * 最大返回的字节数
	 */
	private int maxResultSize = SimpleHttpUtils.MAX_FETCHSIZE;
	/**
	 * 请求头
	 */
	private Map<String,Object> headers = new LinkedHashMap<String,Object>();
	/**
	 * 读超时时间
	 */
	private int readTimeout = SimpleHttpUtils.DEFAULT_READ_TIMEOUT;
	/**
	 * 连接超时时间
	 */
	private int connectTimeout = SimpleHttpUtils.DEFAULT_CONNECT_TIMEOUT;
	/**
	 * 如果状�?�?�?等于200，是�?��?读�?�返回的字节�?
	 */
	private boolean ignoreContentIfUnsuccess = true;
	/**
	 * 请求报文体，�?�有当parameters为空，且请求方�?为post时�?有效
	 */
	private String postData;
	/**
	 * 客户端本地�?书
	 */
	private ClientKeyStore clientKeyStore;
	/**
	 * 客户端信任的�?书
	 */
	private com.roncoo.pay.trade.utils.httpclient.TrustKeyStore TrustKeyStore;
	/**
	 * 如果需�?验�?�?务端�?书，是�?�验�?host与�?书匹�?
	 */
	private boolean hostnameVerify = false;
	
	public SimpleHttpParam(String url){
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
	public Map getParameters() {
		return parameters;
	}
	public void addParameter(String key, String value){
		this.parameters.put(key, value);
	}
	public void addParameters(String key, Collection<String> values){
		this.parameters.put(key, values);
	}
	public void setParameters(Map _parameters) {
		this.parameters.putAll(_parameters);
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public boolean isSslVerify() {
		return sslVerify;
	}
	public void setSslVerify(boolean sslVerify) {
		this.sslVerify = sslVerify;
	}
	public int getMaxResultSize() {
		return maxResultSize;
	}
	public void setMaxResultSize(int maxResultSize) {
		this.maxResultSize = maxResultSize;
	}
	public Map getHeaders() {
		return headers;
	}
	public void addHeader(String key, String value){
		this.headers.put(key, value);
	}
	public void addHeaders(String key, Collection<String> values){
		this.headers.put(key, values);
	}
	public void setHeaders(Map _headers) {
		this.headers.putAll(_headers);
	}
	public int getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public boolean isIgnoreContentIfUnsuccess() {
		return ignoreContentIfUnsuccess;
	}
	public void setIgnoreContentIfUnsuccess(boolean ignoreContentIfUnsuccess) {
		this.ignoreContentIfUnsuccess = ignoreContentIfUnsuccess;
	}
	public String getPostData() {
		return postData;
	}
	public void setPostData(String postData) {
		this.postData = postData;
	}
	
	public ClientKeyStore getClientKeyStore() {
		return clientKeyStore;
	}
	public void setClientKeyStore(ClientKeyStore clientKeyStore) {
		this.clientKeyStore = clientKeyStore;
	}
	public com.roncoo.pay.trade.utils.httpclient.TrustKeyStore getTrustKeyStore() {
		return TrustKeyStore;
	}
	public void setTrustKeyStore(com.roncoo.pay.trade.utils.httpclient.TrustKeyStore trustKeyStore) {
		TrustKeyStore = trustKeyStore;
	}
	public boolean isHostnameVerify() {
		return hostnameVerify;
	}
	public void setHostnameVerify(boolean hostnameVerify) {
		this.hostnameVerify = hostnameVerify;
	}

}
