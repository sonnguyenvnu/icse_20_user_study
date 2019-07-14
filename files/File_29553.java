package com.sohu.cache.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sohu.cache.web.service.AppService;
import com.sohu.cache.web.service.UserLoginStatusService;
import com.sohu.cache.web.service.UserService;
import com.sohu.cache.web.util.DateUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.sohu.cache.constant.AppUserTypeEnum;
import com.sohu.cache.entity.AppToUser;
import com.sohu.cache.entity.AppUser;
import com.sohu.cache.entity.InstanceInfo;
import com.sohu.cache.entity.InstanceSlotModel;
import com.sohu.cache.entity.InstanceStats;
import com.sohu.cache.entity.TimeBetween;
import com.sohu.cache.machine.MachineCenter;
import com.sohu.cache.redis.RedisCenter;

/**
 * 基类controller
 *
 * @author leifu
 * @Time 2014年10月16日
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected UserService userService;
    
    protected AppService appService;
    
    protected MachineCenter machineCenter;
    
    protected UserLoginStatusService userLoginStatusService;
    
    protected RedisCenter redisCenter;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public void setMachineCenter(MachineCenter machineCenter) {
        this.machineCenter = machineCenter;
    }

    public void setUserLoginStatusService(UserLoginStatusService userLoginStatusService) {
        this.userLoginStatusService = userLoginStatusService;
    }

    public void setRedisCenter(RedisCenter redisCenter) {
		this.redisCenter = redisCenter;
	}
    
    protected TimeBetween getJsonTimeBetween(HttpServletRequest request) throws ParseException {
        String startDateParam = request.getParameter("startDate");
        String endDateParam = request.getParameter("endDate");
        Date startDate = DateUtil.parseYYYY_MM_dd(startDateParam);
        Date endDate = DateUtil.parseYYYY_MM_dd(endDateParam);
        long beginTime = NumberUtils.toLong(DateUtil.formatYYYYMMddHHMM(startDate));
        long endTime = NumberUtils.toLong(DateUtil.formatYYYYMMddHHMM(endDate));
        return new TimeBetween(beginTime, endTime, startDate, endDate);
    }
    
    protected TimeBetween getTimeBetween(HttpServletRequest request, Model model, String startDateAtr,
            String endDateAtr) throws ParseException {
        String startDateParam = request.getParameter(startDateAtr);
        String endDateParam = request.getParameter(endDateAtr);
        Date startDate;
        Date endDate;
        if (StringUtils.isBlank(startDateParam) || StringUtils.isBlank(endDateParam)) {
            startDate = new Date();
            endDate = DateUtils.addDays(startDate, 1);
        } else {
            endDate = DateUtil.parseYYYY_MM_dd(endDateParam);
            startDate = DateUtil.parseYYYY_MM_dd(startDateParam);
        }
        Date yesterDay = DateUtils.addDays(startDate, -1);
        
        long beginTime = NumberUtils.toLong(DateUtil.formatYYYYMMddHHMM(startDate));
        long endTime = NumberUtils.toLong(DateUtil.formatYYYYMMddHHMM(endDate));
        model.addAttribute(startDateAtr, startDateParam);
        model.addAttribute(endDateAtr, endDateParam);
        model.addAttribute("yesterDay", DateUtil.formatDate(yesterDay, "yyyy-MM-dd"));
        return new TimeBetween(beginTime, endTime, startDate, endDate);
    }

	/**
     * 返回用户基本信�?�
     *
     * @param request
     * @return
     */
    public AppUser getUserInfo(HttpServletRequest request) {
        long userId = userLoginStatusService.getUserIdFromLoginStatus(request);
        return userService.get(userId);
    }


    /**
     * �?��?json消�?�
     *
     * @param response
     * @param message
     */
    public void sendMessage(HttpServletResponse response, String message) {
        response.reset();
        response.setContentType("application/X-JSON;charset=UTF-8");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.write(message);
        } catch (IOException e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }

    }

    /**
     * @param response
     * @param result
     */
    protected void write(HttpServletResponse response, String result) {
        try {
            response.setContentType("text/javascript");
            response.getWriter().print(result);
            response.getWriter().flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    /**
     * 查看用户对于app�?作的�?��?
     * @param request
     * @param appId
     * @return
     */
    protected boolean checkAppUserProvilege(HttpServletRequest request, long appId) {
        // 当�?用户
        AppUser currentUser = getUserInfo(request);
        if (currentUser == null) {
            logger.error("currentUser is empty");
            return false;
        }
        
        if (AppUserTypeEnum.ADMIN_USER.value().equals(currentUser.getType())) {
            return true;
        }

        // 应用用户列表
        List<AppToUser> appToUsers = appService.getAppToUserList(appId);
        if (CollectionUtils.isEmpty(appToUsers)) {
            logger.error("appId {} userList is empty", appId);
            return false;
        }

        // 应用下用户id集�?�
        Set<Long> appUserIdSet = new HashSet<Long>();
        for (AppToUser appToUser : appToUsers) {
            appUserIdSet.add(appToUser.getUserId());
        }
        
        //最终判断
        if (!appUserIdSet.contains(currentUser.getId())) {
            logger.error("currentUser {} hasn't previlege in appId {}", currentUser.getId(), appId);
            return false;
        }
        return true;
    }
    
    /**
     * 实例统计信�?�
     * 
     * @param appId
     * @param model
     */
    protected void fillAppInstanceStats(Long appId, Model model) {
        // 实例列表
        List<InstanceInfo> instanceList = appService.getAppInstanceInfo(appId);
        model.addAttribute("instanceList", instanceList);
        
        // 实例Map
        Map<Integer, InstanceInfo> instanceInfoMap = new HashMap<Integer, InstanceInfo>();
        for (InstanceInfo instanceInfo : instanceList) {
            instanceInfoMap.put(instanceInfo.getId(), instanceInfo);
        }
        model.addAttribute("instanceInfoMap", instanceInfoMap);
        
        // 实例统计
        List<InstanceStats> appInstanceStats = appService.getAppInstanceStats(appId);
        Map<String, InstanceStats> instanceStatsMap = new HashMap<String, InstanceStats>();
        for (InstanceStats instanceStats : appInstanceStats) {
            instanceStatsMap.put(instanceStats.getIp() + ":" + instanceStats.getPort(), instanceStats);
        }
        model.addAttribute("instanceStatsMap", instanceStatsMap);
        
        //slot分布
        Map<String, InstanceSlotModel> clusterSlotsMap = redisCenter.getClusterSlotsMap(appId);
		model.addAttribute("clusterSlotsMap", clusterSlotsMap);
        
    }
    
    /**
     * 应用机器实例分布图
     * @param appId
     * @param model
     */
    protected void fillAppMachineInstanceTopology(Long appId, Model model) {
        List<InstanceInfo> instanceList = appService.getAppInstanceInfo(appId);
        int groupId = 1;
        // 1.分组，�?�一个主从在一组
        for (int i = 0; i < instanceList.size(); i++) {
            InstanceInfo instance = instanceList.get(i);
            // 有了groupId，�?�?设置
            if (instance.getGroupId() > 0) {
                continue;
            }
            if (instance.isOffline()) {
                continue;
            }
            for (int j = i + 1; j < instanceList.size(); j++) {
                InstanceInfo instanceCompare = instanceList.get(j);
                if (instanceCompare.isOffline()) {
                    continue;
                }
                // 寻找主从对应关系
                if (instanceCompare.getMasterInstanceId() == instance.getId()
                        || instance.getMasterInstanceId() == instanceCompare.getId()) {
                    instanceCompare.setGroupId(groupId);
                }
            }
            instance.setGroupId(groupId++);
        }

        // 2.机器下的实例列表
        Map<String, List<InstanceInfo>> machineInstanceMap = new HashMap<String, List<InstanceInfo>>();
        for (InstanceInfo instance : instanceList) {
            String ip = instance.getIp();
            if (machineInstanceMap.containsKey(ip)) {
                machineInstanceMap.get(ip).add(instance);
            } else {
                List<InstanceInfo> tempInstanceList = new ArrayList<InstanceInfo>();
                tempInstanceList.add(instance);
                machineInstanceMap.put(ip, tempInstanceList);
            }
        }

        model.addAttribute("machineInstanceMap", machineInstanceMap);
        model.addAttribute("instancePairCount", groupId - 1);
    }

}
