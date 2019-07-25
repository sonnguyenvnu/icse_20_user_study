/*-
 * <<
 * task
 * ==
 * Copyright (C) 2019 sia
 * ==
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
 * >>
 */

package com.sia.core.web.vo;

import com.google.common.collect.Maps;
import com.sia.core.helper.JSONHelper;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * 1�?对于�?功的返回，有data的返回data，message是"�?功"，没有data的data为null，message是"�?功"；
 * 2�?对于失败的返回，一般失败返回，message是"失败"，特殊失败返回，message是失败原因。
 * @description JobMTaskVO
 * @see
 * @author MAOZW
 * @date 2018-04-18 11:10
 * @version V1.0.0
 **/
public class ResultBody<T extends Object> implements Serializable {

    /**
     * 返回�?
     */
    private int code = 0;

    /**
     * 错误
     */
    private String error;

    /**
     * 返回消�?�
     */
    private String message;

    /**
     * 返回数�?�
     */
    private T data;

    /**
     * 元数�?�
     */
    private Map<String, Object> extra;

    /**
     * @return true
     */
    public static String isOkay() {
        return JSONHelper.toString(true);
    }

    /**
     * @return false
     */
    public static String isNope() {
        return JSONHelper.toString(false);
    }

    /**
     * @return success
     */
    public static String isSuccess() {
        return "success";
    }

    /**
     * @return fail
     */
    public static String isFail() {
        return "fail";
    }

    /**
     * �?功返回
     *
     * @return message
     */
    public static String success() {
        return toString(new ResultBody().setMessage("�?功"));
    }

    public static <T> String success(T data) {
        return toString(new ResultBody().setData(data).setMessage("�?功"));
    }

    public static <T> String success(String message) {
        return toString(new ResultBody().setMessage(message));
    }

    public static <T> String success(T data, String message) {
        return toString(new ResultBody().setData(data).setMessage(message));
    }

    /**
     * 失败返回
     *
     * @return
     */
    public static String failed() {
        return toString(new ResultBody().setCode(ResultEnum.FAIL.getCode()).setMessage(ResultEnum.FAIL.getMessage()));
    }

    /**
     * 返回失败：包�?�自定义消�?�
     *
     * @param message 失败消�?�
     * @return
     */
    public static String failed(String message) {
        return toString(new ResultBody().setCode(ResultEnum.FAIL.getCode()).setMessage(message));
    }

    /**
     * 返回失败：包�?�自定义消�?� + 返回�?
     *
     * @param code 返回�?
     * @param msg  消�?�
     * @return
     */
    public static String failed(Integer code, String msg) {
        return toString(new ResultBody().setCode(code).setMessage(msg));
    }

    public static String failed(ResultEnum resultEnum) {
        return toString(new ResultBody().setCode(resultEnum.getCode()).setMessage(resultEnum.getMessage()));
    }

    public static String error() {
        return toString(new ResultBody().setCode(ResultEnum.ERROR.getCode()).setMessage(ResultEnum.ERROR.getMessage()));
    }

    public static String toString(Object data) {
        return JSONHelper.toString(data);
    }

    /**
     * 定义返回�?
     */
    public enum ResultEnum {

        /**
         * 返回�? 类型
         */
        SUCCESS(1000, "�?功"),
        FAIL(4000, "失败"),
        WARNING(5000, "警告"),

        /**
         * 客�?端错误
         */
        REQUEST_FAIL_PARAM(4001, "�?�数输入�?正确"),
        REQUEST_FAIL_NOT_AUTH(4002, "没有�?��?"),

        /**
         * �?务端错误
         */
        ERROR(5000, "异常"),
        SERVICE_FAIL_NOT_FOUND(5001, "�?务未找到"),
        TASK_CHECK(5002,"该TASK已被JOB引用，�?能删除"),
        DAG_CHECK(5003, "该TASK�?置存在环路"),
        JOB_NO_TASK_CONFIG(5004, "该JOB没有�?置TASK"),
        TASK_ALREADY_EXISTED(5005, "Task_Key已存在"),
        JOB_ALREADY_EXISTED(5006, "Job_Key已存在");


        int code;

        String message;

        ResultEnum() {
        }

        ResultEnum(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }


    public int getCode() {
        return code;
    }

    public ResultBody setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultBody setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultBody setData(T data) {
        this.data = data;
        return this;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public ResultBody setExtra(Map<String, Object> extra) {
        this.extra = extra;
        return this;
    }

    public ResultBody putExtra(String key, Object value) {
        if (this.extra == null) {
            this.extra = Maps.newHashMap();
        }
        this.extra.put(key, value);
        return this;
    }

}
