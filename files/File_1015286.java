package info.xiaomo.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * �?��?� 最高�?�表�?� �?��?��?� 明日最新�?�始発．．～
 * Today the best performance  as tomorrow newest starter!
 * Created by IntelliJ IDEA.
 *
 * @author : xiaomo
 * github: https://github.com/xiaomoinfo
 * email: xiaomo@xiaomo.info
 * <p>
 * Date: 2016/11/16 10:19
 * Copyright(©) 2015 by xiaomo.
 **/

@Controller
public class FreemarkerController {

    @RequestMapping("hello")
    public String hello(ModelMap map) {
        map.put("host", "使用freemarker!");
        return "index";
    }

}
