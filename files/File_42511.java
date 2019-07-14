package com.roncoo.pay.permission.exception;

import com.roncoo.pay.common.core.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ç®¡ç?†ç³»ç»Ÿæ?ƒé™?æ¨¡å?—å¼‚å¸¸.
 *
 * é¾™æžœå­¦é™¢ï¼šwww.roncoo.com
 * 
 * @authorï¼šshenjialong
 */
public class PermissionException extends BizException {
	private static final long serialVersionUID = -5371846727040729628L;
	private static final Logger logger = LoggerFactory.getLogger(PermissionException.class);
	/** è¯¥ç”¨æˆ·æ²¡æœ‰åˆ†é…?è?œå?•æ?ƒé™? */
	public static final Integer PERMISSION_USER_NOT_MENU = 1001;
	/** æ ¹æ?®è§’è‰²æŸ¥è¯¢è?œå?•å‡ºçŽ°é”™è¯¯ **/
	public static final Integer PERMISSION_QUERY_MENU_BY_ROLE_ERROR = 1002;
	/** åˆ†é…?è?œå?•æ?ƒé™?æ—¶ï¼Œè§’è‰²ä¸?èƒ½ä¸ºç©º **/
	public static final Integer PERMISSION_ASSIGN_MENU_ROLE_NULL = 1003;
	/** å¯¹æŽ¥é¾™æžœå¹³å?°ç”¨æˆ·ä½“ç³»å¼‚å¸¸ **/
	public static final Integer RONCOO_NETWORK_EXCEPTION = 1004;

	public PermissionException() {
	}

	public PermissionException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}

	public PermissionException(int code, String msg) {
		super(code, msg);
	}

	/**
	 * å®žä¾‹åŒ–å¼‚å¸¸
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public PermissionException newInstance(String msgFormat, Object... args) {
		return new PermissionException(this.code, msgFormat, args);
	}

	public PermissionException print() {
		logger.info("==>BizException, code:" + this.code + ", msg:" + this.msg);
		return this;
	}
}
