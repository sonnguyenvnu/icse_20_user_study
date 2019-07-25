package com.lou.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ThymeleafController {

    @GetMapping("/thymeleaf")
    public String hello(HttpServletRequest request, @RequestParam(value = "description", required = false, defaultValue = "springboot-thymeleaf") String description) {
        request.setAttribute("description", description);
        return "thymeleaf";
    }

    @GetMapping("/attributes")
    public String attributes(ModelMap map) {
        // 更改 h1 内容
        map.put("title", "Thymeleaf 标签演示");
        // 更改 id�?name�?value
        map.put("th_id", "thymeleaf-input");
        map.put("th_name", "thymeleaf-input");
        map.put("th_value", "13");
        // 更改 class�?href
        map.put("th_class", "thymeleaf-class");
        map.put("th_href", "http://13blog.site");
        return "attributes";
    }

    @GetMapping("/simple")
    public String simple(ModelMap map) {
        map.put("thymeleafText", "shiyanlou");
        map.put("number1", 2019);
        map.put("number2", 3);
        return "simple";
    }

    @GetMapping("/test")
    public String test(ModelMap map) {
        map.put("title", "Thymeleaf 语法测试");
        map.put("testString", "玩转 Spring Boot");
        map.put("bool", true);
        map.put("testArray", new Integer[]{2018,2019,2020,2021});
        map.put("testList", Arrays.asList("Spring","Spring Boot","Thymeleaf","MyBatis","Java"));
        Map testMap = new HashMap();
        testMap.put("platform", "shiyanlou");
        testMap.put("title", "玩转 Spring Boot");
        testMap.put("author", "�??三");
        map.put("testMap", testMap);
        map.put("testDate", new Date());
        return "test";
    }

}
