package org.jeecgframework.jwt.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.service.TokenManager;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获�?�和删除token的请求地�?�， 
 * 在Restful设计中其实就对应�?�登录和退出登录的资�?映射
 * 
 * @author scott
 * @date 2015/7/30.
 */
@Api(value = "token", description = "鉴�?�token接�?�", tags = "tokenAPI")
@Controller
@RequestMapping("/tokens")
public class TokenController {
	private static final Logger logger = LoggerFactory.getLogger(TokenController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private TokenManager tokenManager;

	@ApiOperation(value = "获�?�TOKEN")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
		logger.info("获�?�TOKEN[{}]" , username);
		// 验�?
		if (StringUtils.isEmpty(username)) {
			return new ResponseEntity("用户账�?��?能为空!", HttpStatus.NOT_FOUND);
		}
		// 验�?
		if (StringUtils.isEmpty(username)) {
			return new ResponseEntity("用户密�?�?能为空!", HttpStatus.NOT_FOUND);
		}
		Assert.notNull(username, "username can not be empty");
		Assert.notNull(password, "password can not be empty");

		TSUser user = userService.checkUserExits(username, password);
		if (user == null) {
			// �??示用户�??或密�?错误
			logger.info("获�?�TOKEN,户账�?�密�?错误[{}]" , username);
			return new ResponseEntity("用户账�?�密�?错误!", HttpStatus.NOT_FOUND);
		}
		// 生�?一个token，�?存用户登录状�?
		String token = tokenManager.createToken(user);
		return new ResponseEntity(token, HttpStatus.OK);
	}

	@ApiOperation(value = "销�?TOKEN")
	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseMessage<?> logout(@ApiParam(name = "username", value = "用户账�?�", required = true) @PathVariable("username") String username) {
		logger.info("deleteToken[{}]" , username);
		// 验�?
		if (StringUtils.isEmpty(username)) {
			return Result.error("用户账�?�，�?能为空!");
		}
		try {
			tokenManager.deleteToken(username);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("销�?TOKEN失败");
		}
		return Result.success();
	}

}
