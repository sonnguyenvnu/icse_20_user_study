package com.myimooc.boot.web.utils;

import com.myimooc.boot.web.model.entity.Result;

/**
 * http请求返回的实体Bean工具类
 * @author ZhangCheng
 * @date 2017-03-19
 * @version  V1.0
 */
public class ResultUtil {
    /** �?功 */
    public final static Integer RESPCODE_SUCCESS =200;
    /** 请求�?�数错误 */
    public final static Integer RESPCODE_ERROR_PARAM =300;
    /** 系统内部业务错误 */
    public final static Integer RESPCODE_ERROR_SERVICE =400;
    /** 系统内部异常 */
    public final static Integer RESPCODE_ERROR_EXECEPTION =500;

    /** 执行�?功，返回�?�数 */
    public  static Result success(Object object){
        Result result = new Result();
        result.setRespCode(ResultUtil.RESPCODE_SUCCESS);
        result.setRespMsg("�?功");
        result.setData(object);
        return result;
    }

    /** 执行�?功，无返回�?�数 */
    public static Result success(){
        return success(null);
    }

    /** 执行错误 */
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setRespCode(code);
        result.setRespMsg(msg);
        return result;
    }

}
