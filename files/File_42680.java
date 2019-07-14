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
package com.roncoo.pay.user.exception;

import com.roncoo.pay.common.core.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 支付业务异常类
 * 龙果学院：www.roncoo.com
 * @author：Peter
 */
public class PayBizException extends BizException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7163126205323125757L;

	/** 支付方�?已存在 **/
    public static final int PAY_TYPE_IS_EXIST = 101;
    
    /** 支付产�?已存在 **/
    public static final int PAY_PRODUCT_IS_EXIST = 102;
    
    /** 支付产�?已关�?�数�?� **/
    public static final int PAY_PRODUCT_HAS_DATA = 103;
    
    /** 用户支付�?置已存在 **/
    public static final int USER_PAY_CONFIG_IS_EXIST = 104;
    
    /** 用户支付�?置�?存在 **/
    public static final int USER_PAY_CONFIG_IS_NOT_EXIST = 105;
    
    /** 用户支付�?置已生效 **/
    public static final int USER_PAY_CONFIG_IS_EFFECTIVE = 106;
    
    /** 支付产�?已生效 **/
    public static final int PAY_PRODUCT_IS_EFFECTIVE = 107;
    
    /** 支付产�?�?存在 **/
    public static final int PAY_PRODUCT_IS_NOT_EXIST = 108;
    
    /** 支付方�?�?存在 **/
    public static final int PAY_TYPE_IS_NOT_EXIST = 108;

    private static final Log LOG = LogFactory.getLog(PayBizException.class);

    public PayBizException() {
    }

    public PayBizException(int code, String msgFormat, Object... args) {
        super(code, msgFormat, args);
    }

    public PayBizException(int code, String msg) {
        super(code, msg);
    }

    public PayBizException print() {
        LOG.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
        return this;
    }
}
