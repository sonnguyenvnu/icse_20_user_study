package com.myimooc.boot.start.exception;

import com.myimooc.boot.start.domain.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <br>
 * 标题: 全局异常�?�获，兼容 web 和 ajax<br>
 * �??述: 使用 @ControllerAdvice 声明当�?是助手类<br>
 *
 * @author zc
 * @date 2018/04/25
 */
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_VIEW = "error";

    @ExceptionHandler(Exception.class)
    public Object handler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        e.printStackTrace();

        if (isAjax(request)) {
            return this.responseByAjax(request, response, e);
        }
        return this.responseByWeb(request, response, e);
    }

    private boolean isAjax(HttpServletRequest request) {
        return (request.getHeader("X-Requested-With") != null) && ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) ;
    }

    private JsonResult responseByAjax(HttpServletRequest request, HttpServletResponse response, Exception e) {
        return JsonResult.errException(e.getMessage());
    }

    private ModelAndView responseByWeb(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ERROR_VIEW);
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        return mav;
    }
}
