package com.myimooc.sso.same.father.demo2.x.com;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.myimooc.sso.same.father.util.HttpUtils;
import com.myimooc.sso.same.father.util.RespMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @author ZhangCheng
 * @date 2017-04-02
 * @version V1.0
 */
@Controller
public class DemoTwoController {

    @RequestMapping("/demo2")
    public ModelAndView main(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        
        //æ ¡éªŒcookieæ˜¯å?¦ä¸ºç©º
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            //æ ¡éªŒcookieæ˜¯å?¦å­˜åœ¨
            for(Cookie cookie : cookies){
                if("ssocookie".equals(cookie.getName())){
                    //å?‘æ ¡éªŒæœ?åŠ¡å™¨å?‘é€?æ ¡éªŒè¯·æ±‚
                    String url = "http://check.x.com:8080/sso/checkCookie";
                    RespMessage respMessage = HttpUtils.doGet(url, cookie.getName(), cookie.getValue());
                    if("200".equals(respMessage.getRespCode())){
                        mv.setViewName("demo2");
                        return mv;
                    }
                }
            }
        }
        mv.addObject("gotoUrl", "http://demo2.x.com:8080/demo2");
        mv.setViewName("login");
        return mv;
    }
}
