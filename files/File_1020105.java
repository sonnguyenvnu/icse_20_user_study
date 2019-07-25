package com.myimooc.freemarker.simple.web.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myimooc.freemarker.simple.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * �?�值控制器
 * @author ZhangCheng
 * @date 2017-03-19
 * @version V1.0
 */
@Controller
@RequestMapping("/")
public class GetVarController {
    
    @GetMapping("/macro")
    public ModelAndView macro(){
        ModelAndView mv= new ModelAndView("macro");
        return mv;
    }
    
    @GetMapping("/method")
    public ModelAndView method(){
        ModelAndView mv= new ModelAndView("method");
        return mv;
    }
    
    @GetMapping("/getvar")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("getvar");
        
        //定义基本数�?�类型
        mv.addObject("intVar",100);
        mv.addObject("longVar",10000L);
        mv.addObject("stringVar","我是字符串");
        mv.addObject("doubleVar",3.45d);
        mv.addObject("booleanVar",true);
        mv.addObject("dateVar",new Date());
        mv.addObject("nullVar","我是null");
        
        //定义�?装对象
        User user = new User();
        user.setName("freemarker");
        mv.addObject("userObj",user);
        
        //定义富文本
        String brief = "<font color='red'>我�?�想早点下�?�。对�?起，你是程�?员�?</font>";
        mv.addObject("briefVar",brief);
        
        //定义List集�?�
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("python");
        list.add("nodejs");
        mv.addObject("myList",list);
        
        //定义Map集�?�
        Map<String,String> map = new HashMap<String,String>(16);
        map.put("java", "你好java");
        map.put("python", "你好python");
        map.put("nodejs", "你好nodejs");
        mv.addObject("map",map);
        
        //定义整数排�?方法
        mv.addObject("sort_int",new SortMethod());
        
        return mv;
    }

}
