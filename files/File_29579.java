package com.sohu.cache.web.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sohu.cache.entity.AppAudit;
import com.sohu.cache.entity.AppUser;
import com.sohu.cache.entity.InstanceInfo;
import com.sohu.cache.redis.RedisCenter;
import com.sohu.cache.redis.RedisDeployCenter;
import com.sohu.cache.stats.instance.InstanceDeployCenter;
import com.sohu.cache.stats.instance.InstanceStatsCenter;
import com.sohu.cache.web.enums.SuccessEnum;

/**
 * 应用�?��?�管�?�
 * 
 * @author leifu
 * @Time 2014年7月3日
 */
@Controller
@RequestMapping("manage/instance")
public class InstanceManageController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(InstanceManageController.class);

    @Resource(name = "instanceDeployCenter")
    private InstanceDeployCenter instanceDeployCenter;
    
    @Resource(name = "redisCenter")
    private RedisCenter redisCenter;
    
    @Resource(name = "instanceStatsCenter")
    private InstanceStatsCenter instanceStatsCenter;
    
    /**
     * 上线(和下线分开)
     * 
     * @param instanceId
     */
    @RequestMapping(value = "/startInstance")
    public ModelAndView doStartInstance(HttpServletRequest request, HttpServletResponse response, Model model, long appId, int instanceId) {
        AppUser appUser = getUserInfo(request);
        logger.warn("user {} startInstance {} ", appUser.getName(), instanceId);
        boolean result = false;
        if (instanceId > 0) {
            try {
                result = instanceDeployCenter.startExistInstance(appId, instanceId);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                model.addAttribute("message", e.getMessage());
            }
        } else {
            logger.error("doStartInstance instanceId:{}", instanceId);
            model.addAttribute("message", "wrong param");
        }
        logger.warn("user {} startInstance {} result is {}", appUser.getName(), instanceId, result);
        if (result) {
            model.addAttribute("success", SuccessEnum.SUCCESS.value());
        } else {
            model.addAttribute("success", SuccessEnum.FAIL.value());
        }
        return new ModelAndView();
    }

    /**
     * 下线实例
     * 
     * @param instanceId
     * @param ip
     */
    @RequestMapping(value = "/shutdownInstance")
    public ModelAndView doShutdownInstance(HttpServletRequest request, HttpServletResponse response, Model model, long appId, int instanceId) {
        AppUser appUser = getUserInfo(request);
        logger.warn("user {} shutdownInstance {} ", appUser.getName(), instanceId);
        boolean result = false;
        if (instanceId > 0) {
            try {
                result = instanceDeployCenter.shutdownExistInstance(appId, instanceId);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                model.addAttribute("message", e.getMessage());
            }
        } else {
            logger.error("doShutdownInstance instanceId:{}", instanceId);
            model.addAttribute("message", "wrong param");
        }
        logger.warn("user {} shutdownInstance {}, result is {}", appUser.getName(), instanceId, result);
        if (result) {
            model.addAttribute("success", SuccessEnum.SUCCESS.value());
        } else {
            model.addAttribute("success", SuccessEnum.FAIL.value());
        }
        return new ModelAndView();
    }
    
    /**
     * 查看redis节点日志
     * @param appId
     * @param slaveInstanceId
     */
    @RequestMapping("/log")
    public ModelAndView doShowLog(HttpServletRequest request, HttpServletResponse response, Model model, int instanceId) {
        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 0);
        if (pageSize == 0) {
            pageSize = 100;
        }
        String instanceLogStr = instanceDeployCenter.showInstanceRecentLog(instanceId, pageSize);
        model.addAttribute("instanceLogList", StringUtils.isBlank(instanceLogStr) ? Collections.emptyList() : Arrays.asList(instanceLogStr.split("\n")));
        return new ModelAndView("manage/instance/log");
    }
    
    /**
     * 处�?�实例�?置修改
     * 
     * @param appAuditId 审批id
     */
    @RequestMapping(value = "/initInstanceConfigChange")
    public ModelAndView doInitInstanceConfigChange(HttpServletRequest request,
            HttpServletResponse response, Model model, Long appAuditId) {
        // 申请原因
        AppAudit appAudit = appService.getAppAuditById(appAuditId);
        model.addAttribute("appAudit", appAudit);

        // 用第一个�?�数存实例id
        Long instanceId = NumberUtils.toLong(appAudit.getParam1());
        Map<String, String> redisConfigList = redisCenter.getRedisConfigList(instanceId.intValue());
        model.addAttribute("redisConfigList", redisConfigList);

        // 实例
        InstanceInfo instanceInfo = instanceStatsCenter.getInstanceInfo(instanceId);
        model.addAttribute("instanceInfo", instanceInfo);
        model.addAttribute("appId", appAudit.getAppId());
        model.addAttribute("appAuditId", appAuditId);

        // 修改�?置的键值对
        model.addAttribute("instanceConfigKey", appAudit.getParam2());
        model.addAttribute("instanceConfigValue", appAudit.getParam3());

        return new ModelAndView("manage/appAudit/initInstanceConfigChange");
    }

    /**
     * 
     * @param appId 应用id
     * @param host 实例ip
     * @param port 实例端�?�
     * @param instanceConfigKey 实例�?置key
     * @param instanceConfigValue 实例�?置value
     * @param appAuditId 审批id
     * @return
     */
    @RequestMapping(value = "/addInstanceConfigChange")
    public ModelAndView doAddAppConfigChange(HttpServletRequest request,
            HttpServletResponse response, Model model, Long appId, String host, int port,
            String instanceConfigKey, String instanceConfigValue, Long appAuditId) {
        AppUser appUser = getUserInfo(request);
        logger.warn("user {} change instanceConfig:appId={},{}:{};key={};value={},appAuditId:{}", appUser.getName(), appId, host, port, instanceConfigKey, instanceConfigValue, appAuditId);
        boolean isModify = false;
        if (StringUtils.isNotBlank(host) && port > 0 && appAuditId != null && StringUtils.isNotBlank(instanceConfigKey) && StringUtils.isNotBlank(instanceConfigValue)) {
            try {
                isModify = instanceDeployCenter.modifyInstanceConfig(appId, appAuditId, host, port, instanceConfigKey, instanceConfigValue);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        logger.warn("user {} change instanceConfig:appId={},{}:{};key={};value={},appAuditId:{},result is:{}", appUser.getName(), appId, host, port, instanceConfigKey, instanceConfigValue, appAuditId, isModify);
        return new ModelAndView("redirect:/manage/app/auditList");
    }
    
    
    
    
    
    
}
