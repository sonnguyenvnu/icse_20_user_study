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
package com.roncoo.pay.controller.exception;

import com.roncoo.pay.common.core.dwz.DWZ;
import com.roncoo.pay.common.core.dwz.DwzAjax;
import com.roncoo.pay.common.core.exception.BizException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Springå¼‚å¸¸æ‹¦æˆªå™¨.
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * @author zenghao
 */
@ControllerAdvice
public class WebExceptionHandler {

	private static final Log LOG = LogFactory.getLog(WebExceptionHandler.class);

	/**
	 * shiroæ?ƒé™? å¼‚å¸¸
	 * <p/>
	 * å?Žç»­æ ¹æ?®ä¸?å?Œçš„éœ€æ±‚å®šåˆ¶å?³å?¯
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.OK)
	public String processUnauthorizedException(HttpServletRequest request, UnauthorizedException e) {
		LOG.error("UnauthorizedException", e);
		DwzAjax dwz = new DwzAjax();
		dwz.setStatusCode(DWZ.ERROR);
		dwz.setMessage("ä½ æ²¡æœ‰æ“?ä½œæ?ƒé™?");
		request.setAttribute("dwz", dwz);
		return "common/ajaxDone";
	}

	/**
	 * ä¸šåŠ¡å¼‚å¸¸
	 * <p/>
	 * å?Žç»­æ ¹æ?®ä¸?å?Œçš„éœ€æ±‚å®šåˆ¶å?³å?¯
	 */
	@ExceptionHandler({ BizException.class })
	@ResponseStatus(HttpStatus.OK)
	public String processBizException(HttpServletRequest request, BizException e) {
		LOG.error("BizException", e);
		DwzAjax dwz = new DwzAjax();
		dwz.setStatusCode(DWZ.ERROR);
		dwz.setMessage(e.getMsg());
		request.setAttribute("dwz", dwz);
		return "common/ajaxDone";
	}

	/**
	 * æ€»å¼‚å¸¸
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public String processException(Exception e, HttpServletRequest request) {
		LOG.error("Exception", e);
		DwzAjax dwz = new DwzAjax();
		dwz.setStatusCode(DWZ.ERROR);
		dwz.setMessage("ç³»ç»Ÿå¼‚å¸¸");
		request.setAttribute("dwz", dwz);
		return "common/ajaxDone";
	}

}
