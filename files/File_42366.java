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
package com.roncoo.pay.common.core.exception;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常 .
 * @company：广州领课网络科技有�?公�?�（龙果学院 www.roncoo.com）.
 * @author along
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -5875371379845226068L;

    /**
     * 数�?�库�?作,insert返回0
     */
    public static final BizException DB_INSERT_RESULT_0 = new BizException(
            10040001, "数�?�库�?作,insert返回0");

    /**
     * 数�?�库�?作,update返回0
     */
    public static final BizException DB_UPDATE_RESULT_0 = new BizException(
            10040002, "数�?�库�?作,update返回0");

    /**
     * 数�?�库�?作,selectOne返回null
     */
    public static final BizException DB_SELECTONE_IS_NULL = new BizException(
            10040003, "数�?�库�?作,selectOne返回null");

    /**
     * 数�?�库�?作,list返回null
     */
    public static final BizException DB_LIST_IS_NULL = new BizException(
            10040004, "数�?�库�?作,list返回null");

    /**
     * Token 验�?�?通过
     */
    public static final BizException TOKEN_IS_ILLICIT = new BizException(
            10040005, "Token 验�?�?�法");
    /**
     * 会�?超时　获�?�session时，如果是空，throws 下�?�这个异常 拦截器会拦截爆会�?超时页�?�
     */
    public static final BizException SESSION_IS_OUT_TIME = new BizException(
            10040006, "会�?超时");

    /**
     * 生�?�?列异常时
     */
    public static final BizException DB_GET_SEQ_NEXT_VALUE_ERROR = new BizException(
            10040007, "�?列生�?超时");

    /**
     * 异常信�?�
     */
    protected String msg;

    /**
     * 具体异常�?
     */
    protected int code;

    public BizException(int code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
        this.msg = String.format(msgFormat, args);
    }

    public BizException() {
        super();
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message) {
        super(message);
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    /**
     * 实例化异常
     * 
     * @param msgFormat
     * @param args
     * @return
     */
    public BizException newInstance(String msgFormat, Object... args) {
        return new BizException(this.code, msgFormat, args);
    }

}
