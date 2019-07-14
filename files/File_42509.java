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
package com.roncoo.pay.permission.entity;

/**
 * �?��?管�?�-�?作员
 *
 * 龙果学院：www.roncoo.com
 * 
 * @author：shenjialong
 */
public class PmsOperator extends PermissionBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loginName;// 登录�??
	private String loginPwd; // 登录密�?
	private String realName; // 姓�??
	private String mobileNo; // 手机�?�
	private String type; // �?作员类型（admin:超级管�?�员，common:普通�?作员），超级管�?�员由系统�?始化时添加，�?能删除
	private String salt;// �?

	/**
	 * 登录�??
	 * 
	 * @return
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 登录�??
	 * 
	 * @return
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 登录密�?
	 * 
	 * @return
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * 登录密�?
	 * 
	 * @return
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	/**
	 * 姓�??
	 * 
	 * @return
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * 姓�??
	 * 
	 * @return
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 手机�?�
	 * 
	 * @return
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * 手机�?�
	 * 
	 * @return
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * �?作员类型
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * �?作员类型
	 * 
	 * @return
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * �?
	 * 
	 * @return
	 */
	public String getsalt() {
		return salt;
	}

	/**
	 * �?
	 * 
	 * @param salt
	 */
	public void setsalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 认�?加密的�?
	 * 
	 * @return
	 */
	public String getCredentialsSalt() {
		return loginName + salt;
	}

	public PmsOperator() {

	}

}
