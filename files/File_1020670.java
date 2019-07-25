package com.github.vole.portal.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class SsoAuthController {


    /**
     * ç”¨æˆ·ä¿¡æ?¯æ ¡éªŒ
     *
     * @param authentication ä¿¡æ?¯
     * @return ç”¨æˆ·ä¿¡æ?¯
     */
    @RequestMapping("/user")
    public Object user(Authentication authentication) {
        if (authentication != null) {
            return authentication.getPrincipal();
        }
        return null;
    }
}
