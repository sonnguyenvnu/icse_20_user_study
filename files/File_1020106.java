package com.myimooc.sso.cross.domain.www.b.com;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myimooc.sso.cross.domain.util.HttpUtils;
import com.myimooc.sso.cross.domain.util.RespMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ZhangCheng
 * @date 2017-04-02
 * @version V1.0
 */
@Controller
@RequestMapping("/b")
public class DemoTwoController {

    @RequestMapping("/demo2")
    public ModelAndView main(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        
        //校验cookie是�?�为空
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            //校验cookie是�?�存在
            for(Cookie cookie : cookies){
                if("ssocookie".equals(cookie.getName())){
                 // �?装请求�?�数
                    Map<String,String> param = new HashMap<String,String>(16);
                    param.put("cookieName", cookie.getName());
                    param.put("cookieValue", cookie.getValue());
                    // �?�校验�?务器�?��?校验请求
                    String url = "http://www.x.com/sso/checkCookie";
                    RespMessage respMessage = HttpUtils.doGet(url, param);
                    // 校验通过
                    if("200".equals(respMessage.getRespCode())){
                        mv.setViewName("demo2");
                        return mv;
                    }
                }
            }
        }
        // 登录失败�?新登录
        mv.addObject("contextPath",request.getContextPath());
        mv.addObject("path","b");
        mv.addObject("gotoUrl", "http://www.b.com/b/demo2");
        mv.setViewName("login");
        return mv;
    }
    
    /**
     * 用户登录
     * @param param
     * @return
     */
    @PostMapping(value="/doLogin")
    @ResponseBody
    public RespMessage doLogin(@RequestParam Map<String,String> param){
        // �?�校验�?务器�?��?校验请求
        String url = "http://www.x.com/sso/doLogin";
        RespMessage respMessage = HttpUtils.doGet(url, param);
        System.out.println("SSO�?务器�?应消�?�："+respMessage);
        return respMessage;
    }
    
    /**
     * �?�当�?域添加cookie
     * @param cookieName
     * @param cookieValue
     * @param response
     */
    @RequestMapping(value="/addCookie")
    public void addCookie(String cookieName,String cookieValue,HttpServletResponse response){
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
