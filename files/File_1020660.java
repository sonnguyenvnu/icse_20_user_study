package com.github.vole.passport.server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/passport")
public class PassportController {

    /**
     * è®¤è¯?é¡µé?¢
     *
     * @return ModelAndView
     */
    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        return new ModelAndView("ftl/login");
    }

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
