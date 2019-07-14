package com.sohu.cache.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sohu.cache.constant.ImportAppResult;
import com.sohu.cache.entity.AppDesc;
import com.sohu.cache.entity.AppUser;
import com.sohu.cache.stats.app.ImportAppCenter;

/**
 * 已�?存在Redis导入
 * 
 * @author leifu
 * @Date 2016-4-16
 * @Time 下�?�2:31:14
 */
@Controller
@RequestMapping("/import/app")
public class ImportAppController extends BaseController {

    @Resource(name = "importAppCenter")
    private ImportAppCenter importAppCenter;

    @RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response, Model model) {
        return new ModelAndView("import/init");
    }

    @RequestMapping(value = "/check")
    public ModelAndView check(HttpServletRequest request, HttpServletResponse response, Model model) {
        AppDesc appDesc = genAppDesc(request);
        String appInstanceInfo = request.getParameter("appInstanceInfo");
        ImportAppResult importAppResult = importAppCenter.check(appDesc, appInstanceInfo);
        model.addAttribute("status", importAppResult.getStatus());
        model.addAttribute("message", importAppResult.getMessage());
        return new ModelAndView("");
    }

    @RequestMapping(value = "/add")
    public ModelAndView add(HttpServletRequest request,
            HttpServletResponse response, Model model) {
        AppDesc appDesc = genAppDesc(request);
        String appInstanceInfo = request.getParameter("appInstanceInfo");
        logger.warn("appDesc:" + appDesc);
        logger.warn("appInstanceInfo: " + appInstanceInfo);

        // �?需�?对格�?进行检验,check已�?�?�过了。
        boolean isSuccess = importAppCenter.importAppAndInstance(appDesc, appInstanceInfo);
        logger.warn("import app result is {}", isSuccess);

        model.addAttribute("status", isSuccess ? 1 : 0);
        return new ModelAndView("");
    }

    /**
     * 生�?AppDesc
     * 
     * @param request
     * @return
     */
    private AppDesc genAppDesc(HttpServletRequest request) {
        // 当�?用户
        AppUser currentUser = getUserInfo(request);
        // 当�?时间
        Date date = new Date();
        // 组装Appdesc
        AppDesc appDesc = new AppDesc();
        appDesc.setName(request.getParameter("name"));
        appDesc.setIntro(request.getParameter("intro"));
        appDesc.setOfficer(request.getParameter("officer"));
        appDesc.setType(NumberUtils.toInt(request.getParameter("type")));
        appDesc.setIsTest(NumberUtils.toInt(request.getParameter("isTest")));
        appDesc.setMemAlertValue(NumberUtils.toInt(request.getParameter("memAlertValue")));
        appDesc.setPassword(request.getParameter("password"));
        appDesc.setUserId(currentUser.getId());
        appDesc.setStatus(2);
        appDesc.setCreateTime(date);
        appDesc.setPassedTime(date);
        appDesc.setVerId(1);

        return appDesc;
    }

}
