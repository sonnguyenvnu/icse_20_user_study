package com.geekq.miaosha.interceptor;

import com.geekq.miasha.enums.resultbean.ResultGeekQ;
import com.geekq.miasha.exception.GlobleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.geekq.miasha.enums.enums.ResultStatus.SESSION_ERROR;
import static com.geekq.miasha.enums.enums.ResultStatus.SYSTEM_ERROR;

/**
 * @author 邱润泽
 *
 * @ExceptionHandler 拦截了异常，我们�?�以通过该注解实现自定义异常处�?�。
 * 其中，@ExceptionHandler �?置的 value 指定需�?拦截的异常类型，上�?�拦截了 Exception.class 这�?异常。
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    public static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value=Exception.class)
    public ResultGeekQ<String> exceptionHandler(Exception e){
        e.printStackTrace();
        if(e instanceof GlobleException){
            GlobleException ex= (GlobleException)e;
            return ResultGeekQ.error(ex.getStatus());
        }else if( e instanceof BindException){
            BindException ex = (BindException) e  ;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            /**
             * 打�?�堆栈信�?�
             */
            logger.error(String.format(msg, msg));
            return ResultGeekQ.error(SESSION_ERROR);
        }else {
            return ResultGeekQ.error(SYSTEM_ERROR);
        }
    }
}
