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
package com.roncoo.pay.account.exception;

import com.roncoo.pay.common.core.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 账户�?务业务异常类,异常代�?8�?数字组�?,�?4�?固定1001打头,�?�4�?自定义
 * 龙果学院：www.roncoo.com
 * @author：zenghao
 */
public class AccountBizException extends BizException {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(AccountBizException.class);

	public static final AccountBizException ACCOUNT_NOT_EXIT = new AccountBizException(10010001, "账户�?存在");
	public static final AccountBizException ACCOUNT_SUB_AMOUNT_OUTLIMIT = new AccountBizException(10010002, "余�?�?足，�?款超�?");
	public static final AccountBizException ACCOUNT_UN_FROZEN_AMOUNT_OUTLIMIT = new AccountBizException(10010003, "解冻金�?超�?");
	public static final AccountBizException ACCOUNT_FROZEN_AMOUNT_OUTLIMIT = new AccountBizException(10010004, "冻结金�?超�?");
	public static final AccountBizException ACCOUNT_TYPE_IS_NULL = new AccountBizException(10010005, "账户类型�?能为空");

	public AccountBizException() {
	}

	public AccountBizException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public AccountBizException(int code, String msg) {
		super(code, msg);
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public AccountBizException newInstance(String msgFormat, Object... args) {
		return new AccountBizException(this.code, msgFormat, args);
	}

	public AccountBizException print() {
		logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
		return this;
	}
}
