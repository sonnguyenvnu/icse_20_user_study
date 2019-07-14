package com.sohu.cache.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sohu.cache.client.service.AppInstanceClientRelationService;
import com.sohu.cache.client.service.ClientReportCostDistriService;
import com.sohu.cache.client.service.ClientReportExceptionService;
import com.sohu.cache.client.service.ClientReportValueDistriService;
import com.sohu.cache.entity.AppClientCostTimeStat;
import com.sohu.cache.entity.AppClientCostTimeTotalStat;
import com.sohu.cache.entity.AppClientExceptionStat;
import com.sohu.cache.entity.AppClientValueDistriSimple;
import com.sohu.cache.entity.AppDesc;
import com.sohu.cache.entity.AppInstanceClientRelation;
import com.sohu.cache.entity.TimeBetween;
import com.sohu.cache.stats.instance.InstanceStatsCenter;
import com.sohu.cache.web.service.AppService;
import com.sohu.cache.web.util.DateUtil;
import com.sohu.cache.web.util.Page;
import com.sohu.tv.jedis.stat.utils.NumberUtil;

/**
 * 应用客户端统计相关
 * 
 * @author leifu
 * @Time 2014年8月31日
 */
@Controller
@RequestMapping("/client/show")
public class AppClientDataShowController extends BaseController {
    
    /**
     * 客户端耗时�?务
     */
    @Resource(name = "clientReportCostDistriService")
    private ClientReportCostDistriService clientReportCostDistriService;

    /**
     * 客户端异常�?务
     */
    @Resource(name = "clientReportExceptionService")
    private ClientReportExceptionService clientReportExceptionService;

    /**
     * 客户端值分布�?务
     */
    @Resource(name = "clientReportValueDistriService")
    private ClientReportValueDistriService clientReportValueDistriService;
    
    /**
     * 应用基本�?务
     */
    @Resource(name = "appService")
    private AppService appService;
    
    /**
     * 实例信�?�
     */
    @Resource(name = "instanceStatsCenter")
    private InstanceStatsCenter instanceStatsCenter;
    
    /**
     * 应用下节点和客户端关系�?务
     */
    @Resource(name = "appInstanceClientRelationService")
    private AppInstanceClientRelationService appInstanceClientRelationService;

    /**
     * 收集数�?�时间format
     */
    private final static String COLLECT_TIME_FORMAT = "yyyyMMddHHmmss";

    /**
     * 应用客户端统计首页
     * 
     * @param appId 应用id
     */
    @RequestMapping("/index")
    public ModelAndView doIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
        Long appId = NumberUtils.toLong(request.getParameter("appId"));
        if (appId == null || appId <= 0) {
            return new ModelAndView("");
        }
        AppDesc appDesc = appService.getByAppId(appId);
        model.addAttribute("appId", appId);
        model.addAttribute("appDesc", appDesc);
        model.addAttribute("tabTag", request.getParameter("tabTag"));
        model.addAttribute("type", request.getParameter("type"));
        model.addAttribute("startDate", request.getParameter("startDate"));
        model.addAttribute("endDate", request.getParameter("endDate"));
        model.addAttribute("exceptionStartDate", request.getParameter("exceptionStartDate"));
        model.addAttribute("exceptionEndDate", request.getParameter("exceptionEndDate"));
        model.addAttribute("valueDistriStartDate", request.getParameter("valueDistriStartDate"));
        model.addAttribute("valueDistriEndDate", request.getParameter("valueDistriEndDate"));
        model.addAttribute("costDistriStartDate", request.getParameter("costDistriStartDate"));
        model.addAttribute("costDistriEndDate", request.getParameter("costDistriEndDate"));
        model.addAttribute("clientIp", request.getParameter("clientIp"));
        model.addAttribute("pageNo", request.getParameter("pageNo"));
        model.addAttribute("firstCommand", request.getParameter("firstCommand"));
        model.addAttribute("timeDimensionality", request.getParameter("timeDimensionality"));
        return new ModelAndView("client/appClientIndex");
    }
    
    /**
     * 客户端异常查询
     */
    @RequestMapping("/exception")
    public ModelAndView doException(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 1.1 应用信�?�
        Long appId = NumberUtils.toLong(request.getParameter("appId"));
        if (appId <= 0) {
            return new ModelAndView("");
        }
        AppDesc appDesc = appService.getByAppId(appId);
        model.addAttribute("appDesc", appDesc);

        // 1.2 异常类型
        int type = NumberUtil.toInt(request.getParameter("type"));
        model.addAttribute("type", type);

        // 1.3 客户端ip
        String clientIp = request.getParameter("clientIp");
        model.addAttribute("clientIp", clientIp);

        // 1.4 日期格�?转�?�
        TimeBetween timeBetween = new TimeBetween();
        try {
            timeBetween = fillWithClientExceptionTime(request, model);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }

        // 2. 分页查询异常
        int totalCount = clientReportExceptionService.getAppExceptionCount(appId, timeBetween.getStartTime(), timeBetween.getEndTime(), type, clientIp);
        int pageNo = NumberUtils.toInt(request.getParameter("pageNo"), 1);
        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 10);
        Page page = new Page(pageNo,pageSize, totalCount);
        model.addAttribute("page", page);
        
        List<AppClientExceptionStat> appClientExceptionList = clientReportExceptionService.getAppExceptionList(appId,
                timeBetween.getStartTime(), timeBetween.getEndTime(), type, clientIp, page);
        model.addAttribute("appClientExceptionList", appClientExceptionList);

        return new ModelAndView("client/clientException");
    }
    
    /**
     * 异常查询日期格�?
     */
    private TimeBetween fillWithClientExceptionTime(HttpServletRequest request, Model model) throws ParseException {
        final String exceptionDateFormat = "yyyy-MM-dd";
        String exceptionStartDateParam = request.getParameter("exceptionStartDate");
        String exceptionEndDateParam = request.getParameter("exceptionEndDate");
        Date startDate;
        Date endDate;
        if (StringUtils.isBlank(exceptionStartDateParam) || StringUtils.isBlank(exceptionEndDateParam)) {
            // 如果为空默认�?�昨天和今天
            SimpleDateFormat sdf = new SimpleDateFormat(exceptionDateFormat);
            startDate = sdf.parse(sdf.format(new Date()));
            endDate = DateUtils.addDays(startDate, 1);
            exceptionStartDateParam = DateUtil.formatDate(startDate, exceptionDateFormat);
            exceptionEndDateParam = DateUtil.formatDate(endDate, exceptionDateFormat);
        } else {
            endDate = DateUtil.parse(exceptionEndDateParam, exceptionDateFormat);
            startDate = DateUtil.parse(exceptionStartDateParam, exceptionDateFormat);
            //�?制�?能超过7天
            if (endDate.getTime() - startDate.getTime() > TimeUnit.DAYS.toMillis(7)) {
                startDate = DateUtils.addDays(endDate, -7);
            }
        }
        // �?端需�?
        model.addAttribute("exceptionStartDate", exceptionStartDateParam);
        model.addAttribute("exceptionEndDate", exceptionEndDateParam);
        // 查询�?��?�需�?
        long startTime = NumberUtils.toLong(DateUtil.formatDate(startDate, COLLECT_TIME_FORMAT));
        long endTime = NumberUtils.toLong(DateUtil.formatDate(endDate, COLLECT_TIME_FORMAT));
        return new TimeBetween(startTime, endTime, startDate, endDate);
    }

    /**
     * 应用客户端耗时统计
     */
    @RequestMapping("/costDistribute")
    public ModelAndView doCostDistribute(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 1.应用信�?�
        Long appId = NumberUtils.toLong(request.getParameter("appId"));
        if (appId <= 0) {
            return new ModelAndView("");
        }
        AppDesc appDesc = appService.getByAppId(appId);
        model.addAttribute("appDesc", appDesc);
        model.addAttribute("appId", appId);

        // 2.获�?�时间区间
        TimeBetween timeBetween = new TimeBetween();
        try {
            timeBetween = fillWithCostDateFormat(request, model);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        long startTime = timeBetween.getStartTime();
        long endTime = timeBetween.getEndTime();
        Date startDate = timeBetween.getStartDate();
        
        // 3.所有命令和第一个命令
        List<String> allCommands = clientReportCostDistriService.getAppDistinctCommand(appId, startTime, endTime);
        model.addAttribute("allCommands", allCommands);
        
        // 4.所有客户端和实例对应关系
        List<AppInstanceClientRelation> appInstanceClientRelationList = appInstanceClientRelationService.getAppInstanceClientRelationList(appId, startDate);
        model.addAttribute("appInstanceClientRelationList", appInstanceClientRelationList);
        
        String firstCommand = request.getParameter("firstCommand");
        if (StringUtils.isBlank(firstCommand) && CollectionUtils.isNotEmpty(allCommands)) {
            firstCommand = allCommands.get(0);
            model.addAttribute("firstCommand", firstCommand);
        } else {
            model.addAttribute("firstCommand", firstCommand);
        }
        
        // 5.1 应用下客户端和实例的全局耗时统计列表
        List<AppClientCostTimeTotalStat> appChartStatList = clientReportCostDistriService.getAppClientCommandTotalStat(appId, firstCommand, startTime, endTime);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        // 5.2 简化字段
        List<Map<String, Object>> app = new ArrayList<Map<String, Object>>();
        for (AppClientCostTimeTotalStat appClientCostTimeTotalStat : appChartStatList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("timeStamp", appClientCostTimeTotalStat.getTimeStamp());
            map.put("count", appClientCostTimeTotalStat.getTotalCount());
            map.put("mean", appClientCostTimeTotalStat.getMean());
            map.put("median", appClientCostTimeTotalStat.getMedian());
            map.put("max90", appClientCostTimeTotalStat.getNinetyPercentMax());
            map.put("max99", appClientCostTimeTotalStat.getNinetyNinePercentMax());
            map.put("max100", appClientCostTimeTotalStat.getHundredMax());
            map.put("maxInst", appClientCostTimeTotalStat.getMaxInstanceHost() + ":" + appClientCostTimeTotalStat.getMaxInstancePort());
            map.put("maxClient", appClientCostTimeTotalStat.getMaxClientIp());
            app.add(map);
        }
        
        resultMap.put("app", app);
        model.addAttribute("appChartStatListJson", JSONObject.toJSONString(resultMap));
        
        return new ModelAndView("client/clientCostDistribute");
    }
    
    /**
     * 获�?�耗时时间区间
     * @throws ParseException 
     */
    private TimeBetween fillWithCostDateFormat(HttpServletRequest request, Model model) throws ParseException {
        
        final String costDistriDateFormat = "yyyy-MM-dd";
        String costDistriStartDateParam = request.getParameter("costDistriStartDate");
        String costDistriEndDateParam = request.getParameter("costDistriEndDate");
        Date startDate;
        Date endDate;
        if (StringUtils.isBlank(costDistriStartDateParam) || StringUtils.isBlank(costDistriEndDateParam)) {
            // 如果为空默认�?�昨天和今天
            SimpleDateFormat sdf = new SimpleDateFormat(costDistriDateFormat);
            startDate = sdf.parse(sdf.format(new Date()));
            endDate = DateUtils.addDays(startDate, 1);
            costDistriStartDateParam = DateUtil.formatDate(startDate, costDistriDateFormat);
            costDistriEndDateParam = DateUtil.formatDate(endDate, costDistriDateFormat);
        } else {
            endDate = DateUtil.parse(costDistriEndDateParam, costDistriDateFormat);
            startDate = DateUtil.parse(costDistriStartDateParam, costDistriDateFormat);
            //�?制�?能超过1天
            if (endDate.getTime() - startDate.getTime() > TimeUnit.DAYS.toMillis(1)) {
                startDate = DateUtils.addDays(endDate, -1);
            }
        }
        // �?端需�?
        model.addAttribute("costDistriStartDate", costDistriStartDateParam);
        model.addAttribute("costDistriEndDate", costDistriEndDateParam);
        // 查询�?��?�需�?
        long startTime = NumberUtils.toLong(DateUtil.formatDate(startDate, COLLECT_TIME_FORMAT));
        long endTime = NumberUtils.toLong(DateUtil.formatDate(endDate, COLLECT_TIME_FORMAT));
        return new TimeBetween(startTime, endTime, startDate, endDate);
    }

    /**
     * 获�?�指定时间内�?个命令�?个客户端和实例的统计数�?�
     * @param appId
     */
    @RequestMapping("/getAppClientInstanceCommandCost")
    public ModelAndView doGetAppClientInstanceCommandCost(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        final String costDistriDateFormat = "yyyy-MM-dd";
        long appId = NumberUtils.toLong(request.getParameter("appId"));
        //时间转�?�
        String costDistriStartDate = request.getParameter("costDistriStartDate");
        String costDistriEndDate = request.getParameter("costDistriEndDate");
        Date startDate = DateUtil.parse(costDistriStartDate, costDistriDateFormat);
        Date endDate = DateUtil.parse(costDistriEndDate, costDistriDateFormat);
        long startTime = NumberUtils.toLong(DateUtil.formatDate(startDate, COLLECT_TIME_FORMAT));
        long endTime = NumberUtils.toLong(DateUtil.formatDate(endDate, COLLECT_TIME_FORMAT));
        
        String firstCommand = request.getParameter("firstCommand");
        long instanceId = NumberUtils.toLong(request.getParameter("instanceId"));
        String clientIp = request.getParameter("clientIp");
        
        //客户端和实例统计
        List<AppClientCostTimeStat> clientInstanceChartStatList = clientReportCostDistriService.getAppCommandClientToInstanceStat(appId, firstCommand, instanceId, clientIp, startTime, endTime);
        //缩�?字段
        List<Map<String, Object>> clientInstanceStat = new ArrayList<Map<String, Object>>();
        for (AppClientCostTimeStat appClientCostTimeStat : clientInstanceChartStatList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("timeStamp", appClientCostTimeStat.getTimeStamp());
            map.put("count", appClientCostTimeStat.getCount());
            map.put("mean", appClientCostTimeStat.getMean());
            map.put("median", appClientCostTimeStat.getMedian());
            map.put("max90", appClientCostTimeStat.getNinetyPercentMax());
            map.put("max99", appClientCostTimeStat.getNinetyNinePercentMax());
            map.put("max100", appClientCostTimeStat.getHundredMax());
            clientInstanceStat.add(map);
        }
        //生�?数�?�map json
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("clientInstanceStat", clientInstanceStat);
        sendMessage(response, JSONObject.toJSONString(resultMap));
        return null;
    }

    /**
     * 应用客户端值分布相关
     */
    @RequestMapping("/valueDistribute")
    public ModelAndView doValueDistribute(HttpServletRequest request, HttpServletResponse response, Model model)
            throws ParseException {
        // 1.1 应用信�?�
        Long appId = NumberUtils.toLong(request.getParameter("appId"));
        if (appId <= 0) {
            return new ModelAndView("");
        }
        AppDesc appDesc = appService.getByAppId(appId);
        model.addAttribute("appDesc", appDesc);

        // 1.2 时间格�?转�?�
        TimeBetween timeBetween = new TimeBetween();
        try {
            timeBetween = fillWithValueDistriTime(request, model);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        long startTime = timeBetween.getStartTime();
        long endTime = timeBetween.getEndTime();
        
        //值分布列表
        List<AppClientValueDistriSimple> appClientValueDistriSimpleList = clientReportValueDistriService.getAppValueDistriList(appId, startTime, endTime);
        model.addAttribute("appClientValueDistriSimpleList", appClientValueDistriSimpleList);
        
        //值分布json
        model.addAttribute("appClientValueDistriSimpleListJson", JSONObject.toJSONString(appClientValueDistriSimpleList));
        
        return new ModelAndView("client/clientValueDistribute");
    }
    
    /**
     * 值分布日期格�?
     */
    private TimeBetween fillWithValueDistriTime(HttpServletRequest request, Model model) throws ParseException {
        final String valueDistriDateFormat = "yyyy-MM-dd";
        String valueDistriStartDateParam = request.getParameter("valueDistriStartDate");
        String valueDistriEndDateParam = request.getParameter("valueDistriEndDate");
        Date startDate;
        Date endDate;
        if (StringUtils.isBlank(valueDistriStartDateParam) || StringUtils.isBlank(valueDistriEndDateParam)) {
            // 如果为空默认�?�昨天和今天
            SimpleDateFormat sdf = new SimpleDateFormat(valueDistriDateFormat);
            startDate = sdf.parse(sdf.format(new Date()));
            endDate = DateUtils.addDays(startDate, 1);
            valueDistriStartDateParam = DateUtil.formatDate(startDate, valueDistriDateFormat);
            valueDistriEndDateParam = DateUtil.formatDate(endDate, valueDistriDateFormat);
        } else {
            endDate = DateUtil.parse(valueDistriEndDateParam, valueDistriDateFormat);
            startDate = DateUtil.parse(valueDistriStartDateParam, valueDistriDateFormat);
            //�?制�?能超过1天
            if (endDate.getTime() - startDate.getTime() > TimeUnit.DAYS.toMillis(1)) {
                startDate = DateUtils.addDays(endDate, -1);
            }
        }
        // �?端需�?
        model.addAttribute("valueDistriStartDate", valueDistriStartDateParam);
        model.addAttribute("valueDistriEndDate", valueDistriEndDateParam);
        // 查询�?��?�需�?
        long startTime = NumberUtils.toLong(DateUtil.formatDate(startDate, COLLECT_TIME_FORMAT));
        long endTime = NumberUtils.toLong(DateUtil.formatDate(endDate, COLLECT_TIME_FORMAT));
        return new TimeBetween(startTime, endTime, startDate, endDate);
    }
    
}
