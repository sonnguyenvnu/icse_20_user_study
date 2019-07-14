/*
 * Copyright 2019 http://www.hswebframework.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.hswebframework.web.authorization.basic.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.SneakyThrows;
import org.hswebframework.web.WebUtil;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.AuthenticationManager;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.authorization.listener.event.*;
import org.hswebframework.web.authorization.simple.PlainTextUsernamePasswordAuthenticationRequest;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.hswebframework.web.logging.AccessLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static org.hswebframework.web.controller.message.ResponseMessage.ok;

/**
 * @author zhouhao
 */
@RestController
@RequestMapping("${hsweb.web.mappings.authorize:authorize}")
@AccessLogger("æŽˆæ?ƒ")
@Api(tags = "æ?ƒé™?-ç”¨æˆ·æŽˆæ?ƒ", value = "æŽˆæ?ƒ")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping({"/login-out", "/sign-out", "/exit"})
    @Authorize
    @ApiOperation("é€€å‡ºå½“å‰?ç™»å½•")
    public ResponseMessage exit(@ApiParam(hidden = true) Authentication authentication) {
        eventPublisher.publishEvent(new AuthorizationExitEvent(authentication));
        return ok();
    }

    @GetMapping("/me")
    @Authorize
    @ApiOperation("å½“å‰?ç™»å½•ç”¨æˆ·æ?ƒé™?ä¿¡æ?¯")
    public ResponseMessage<Authentication> me(@ApiParam(hidden = true) Authentication authentication) {
        return ok(authentication);
    }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("ç”¨æˆ·å??å¯†ç ?ç™»å½•,jsonæ–¹å¼?")
    public ResponseMessage<Map<String, Object>> authorize(@ApiParam(example = "{\"username\":\"admin\",\"password\":\"admin\"}")
                                                          @RequestBody Map<String, String> parameter) {


        return doLogin(parameter.get("username"), parameter.get("password"), parameter);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation("ç”¨æˆ·å??å¯†ç ?ç™»å½•,å?‚æ•°æ–¹å¼?")
    public ResponseMessage<Map<String, Object>> authorize(@RequestParam @ApiParam("ç”¨æˆ·å??") String username,
                                                          @RequestParam @ApiParam("å¯†ç ?") String password,
                                                          @ApiParam(hidden = true) HttpServletRequest request) {

        return doLogin(username, password, WebUtil.getParameters(request));
    }

    /**
     * <img src="https://raw.githubusercontent.com/hs-web/hsweb-framework/3.0.x/hsweb-authorization/hsweb-authorization-basic/img/autz-flow.png">
     */
    @SneakyThrows
    protected ResponseMessage<Map<String, Object>> doLogin(String username, String password, Map<String, ?> parameter) {
        Assert.hasLength(username, "ç”¨æˆ·å??ä¸?èƒ½ä¸ºç©º");
        Assert.hasLength(password, "å¯†ç ?ä¸?èƒ½ä¸ºç©º");

        AuthorizationFailedEvent.Reason reason = AuthorizationFailedEvent.Reason.OTHER;
        Function<String, Object> parameterGetter = parameter::get;
        try {
            AuthorizationDecodeEvent decodeEvent = new AuthorizationDecodeEvent(username, password, parameterGetter);
            eventPublisher.publishEvent(decodeEvent);
            username = decodeEvent.getUsername();
            password = decodeEvent.getPassword();
            AuthorizationBeforeEvent beforeEvent = new AuthorizationBeforeEvent(username, password, parameterGetter);
            eventPublisher.publishEvent(beforeEvent);
            // éªŒè¯?é€šè¿‡
            Authentication authentication = authenticationManager.authenticate(new PlainTextUsernamePasswordAuthenticationRequest(username, password));

            //è§¦å?‘æŽˆæ?ƒæˆ?åŠŸäº‹ä»¶
            AuthorizationSuccessEvent event = new AuthorizationSuccessEvent(authentication, parameterGetter);
            event.getResult().put("userId", authentication.getUser().getId());
            eventPublisher.publishEvent(event);
            return ok(event.getResult());
        } catch (Exception e) {
            AuthorizationFailedEvent failedEvent = new AuthorizationFailedEvent(username, password, parameterGetter, reason);
            failedEvent.setException(e);
            eventPublisher.publishEvent(failedEvent);
            throw failedEvent.getException();
        }
    }

}
