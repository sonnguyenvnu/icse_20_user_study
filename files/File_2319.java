package com.zheng.common.base;

import com.zheng.common.util.PropertiesFileUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.InvalidSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制器基类
 * Created by ZhangShuzheng on 2017/2/4.
 */
public abstract class BaseController {

	private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 统一异常处�?�
	 * @param request
	 * @param response
	 * @param exception
	 */
	@ExceptionHandler
	public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		LOGGER.error("统一异常处�?�：", exception);
		request.setAttribute("ex", exception);
		if (null != request.getHeader("X-Requested-With") && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			request.setAttribute("requestHeader", "ajax");
		}
		// shiro没有�?��?异常
		if (exception instanceof UnauthorizedException) {
			return "/403.jsp";
		}
		// shiro会�?已过期异常
		if (exception instanceof InvalidSessionException) {
			return "/error.jsp";
		}
		return "/error.jsp";
	}

	/**
	 * 返回jsp视图
	 * @param path
	 * @return
	 */
	public static String jsp(String path) {
		return path.concat(".jsp");
	}

	/**
	 * 返回thymeleaf视图
	 * @param path
	 * @return
	 */
	public static String thymeleaf(String path) {
		String folder = PropertiesFileUtil.getInstance().get("app.name");
		return "/".concat(folder).concat(path).concat(".html");
	}

}
