package com.sohu.cache.web.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sohu.cache.constant.CommandResult;
import com.sohu.cache.constant.MachineInfoEnum;
import com.sohu.cache.constant.AppDataMigrateEnum;
import com.sohu.cache.constant.AppDataMigrateResult;
import com.sohu.cache.constant.RedisMigrateToolConstant;
import com.sohu.cache.entity.AppDataMigrateSearch;
import com.sohu.cache.entity.AppDataMigrateStatus;
import com.sohu.cache.entity.AppDesc;
import com.sohu.cache.entity.AppUser;
import com.sohu.cache.entity.InstanceInfo;
import com.sohu.cache.entity.MachineInfo;
import com.sohu.cache.machine.MachineCenter;
import com.sohu.cache.redis.RedisCenter;
import com.sohu.cache.stats.app.AppDataMigrateCenter;
import com.sohu.cache.util.ConstUtils;
import com.sohu.cache.util.TypeUtil;
import com.sohu.cache.web.service.AppService;

/**
 * 应用数�?��?移入�?�
 * 
 * @author leifu
 * @Date 2016-6-8
 * @Time 下�?�11:10:34
 */
@Controller
@RequestMapping("/data/migrate")
public class AppDataMigrateController extends BaseController {

    @Resource(name = "appDataMigrateCenter")
    private AppDataMigrateCenter appDataMigrateCenter;
    
    @Resource(name = "appService")
    private AppService appService;
    
    @Resource(name = "machineCenter")
    private MachineCenter machineCenter;
    
    @Resource(name = "redisCenter")
    private RedisCenter redisCenter;
    
    private static Set<String> MIGRATE_SAMPLE_USEFUL_LINES = new HashSet<String>();
    static {
        MIGRATE_SAMPLE_USEFUL_LINES.add("Checked keys");
        MIGRATE_SAMPLE_USEFUL_LINES.add("Inconsistent value keys");
        MIGRATE_SAMPLE_USEFUL_LINES.add("Inconsistent expire keys");
        MIGRATE_SAMPLE_USEFUL_LINES.add("Other check error keys");
        MIGRATE_SAMPLE_USEFUL_LINES.add("Checked OK keys");
    }
    

    /**
     * �?始化界�?�
     * @return
     */
    @RequestMapping(value = "/init")
    public ModelAndView init(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<MachineInfo> machineInfoList = machineCenter.getMachineInfoByType(MachineInfoEnum.TypeEnum.REDIS_MIGRATE_TOOL);
        model.addAttribute("machineInfoList", machineInfoList);
        return new ModelAndView("migrate/init");
    }

    /**
     * 检查�?置
     * @return
     */
    @RequestMapping(value = "/check")
    public ModelAndView check(HttpServletRequest request, HttpServletResponse response, Model model) {
        //相关�?�数
        String migrateMachineIp = request.getParameter("migrateMachineIp");
        String sourceRedisMigrateIndex = request.getParameter("sourceRedisMigrateIndex");
        AppDataMigrateEnum sourceRedisMigrateEnum = AppDataMigrateEnum.getByIndex(NumberUtils.toInt(sourceRedisMigrateIndex, -1));
        String sourceServers = request.getParameter("sourceServers");
        String targetRedisMigrateIndex = request.getParameter("targetRedisMigrateIndex");
        AppDataMigrateEnum targetRedisMigrateEnum = AppDataMigrateEnum.getByIndex(NumberUtils.toInt(targetRedisMigrateIndex, -1));
        String targetServers = request.getParameter("targetServers");
        String redisSourcePass = request.getParameter("redisSourcePass");
        String redisTargetPass = request.getParameter("redisTargetPass");

        //检查返回结果
        AppDataMigrateResult redisMigrateResult = appDataMigrateCenter.check(migrateMachineIp, sourceRedisMigrateEnum, sourceServers, targetRedisMigrateEnum, targetServers, redisSourcePass, redisTargetPass);
        model.addAttribute("status", redisMigrateResult.getStatus());
        model.addAttribute("message", redisMigrateResult.getMessage());
        return new ModelAndView("");
    }

    /**
     * 开始�?移
     * @return
     */
    @RequestMapping(value = "/start")
    public ModelAndView start(HttpServletRequest request, HttpServletResponse response, Model model) {
        //相关�?�数
        String migrateMachineIp = request.getParameter("migrateMachineIp");
        String sourceRedisMigrateIndex = request.getParameter("sourceRedisMigrateIndex");
        AppDataMigrateEnum sourceRedisMigrateEnum = AppDataMigrateEnum.getByIndex(NumberUtils.toInt(sourceRedisMigrateIndex, -1));
        String sourceServers = request.getParameter("sourceServers");
        String targetRedisMigrateIndex = request.getParameter("targetRedisMigrateIndex");
        AppDataMigrateEnum targetRedisMigrateEnum = AppDataMigrateEnum.getByIndex(NumberUtils.toInt(targetRedisMigrateIndex, -1));
        String targetServers = request.getParameter("targetServers");
        long sourceAppId = NumberUtils.toLong(request.getParameter("sourceAppId"));
        long targetAppId = NumberUtils.toLong(request.getParameter("targetAppId"));
        String redisSourcePass = request.getParameter("redisSourcePass");
        String redisTargetPass = request.getParameter("redisTargetPass");

        AppUser appUser = getUserInfo(request);
        long userId = appUser == null ? 0 : appUser.getId();

        // �?需�?对格�?进行检验,check已�?�?�过了，开始�?移
        boolean isSuccess = appDataMigrateCenter.migrate(migrateMachineIp, sourceRedisMigrateEnum, sourceServers,
                targetRedisMigrateEnum, targetServers, sourceAppId, targetAppId, redisSourcePass, redisTargetPass, userId);

        model.addAttribute("status", isSuccess ? 1 : 0);
        return new ModelAndView("");
    }
    
    /**
     * �?�掉�?移任务
     * @return
     */
    @RequestMapping(value = "/stop")
    public ModelAndView stop(HttpServletRequest request, HttpServletResponse response, Model model) {
        //任务id：查到任务相关信�?�
        long id = NumberUtils.toLong(request.getParameter("id"));
        AppDataMigrateResult stopMigrateResult = appDataMigrateCenter.stopMigrate(id);
        model.addAttribute("status", stopMigrateResult.getStatus());
        model.addAttribute("message", stopMigrateResult.getMessage());
        return new ModelAndView("");
    }
    
    /**
     * 查看�?移日志
     * @return
     */
    @RequestMapping(value = "/log")
    public ModelAndView log(HttpServletRequest request, HttpServletResponse response, Model model) {
        //任务id：查到任务相关信�?�
        long id = NumberUtils.toLong(request.getParameter("id"));
        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"), 0);
        if (pageSize == 0) {
            pageSize = 100;
        }
        
        String log = appDataMigrateCenter.showDataMigrateLog(id, pageSize);
        model.addAttribute("logList", Arrays.asList(log.split(ConstUtils.NEXT_LINE)));
        return new ModelAndView("migrate/log");
    }
    
    /**
     * 查看�?移日志
     * @return
     */
    @RequestMapping(value = "/config")
    public ModelAndView config(HttpServletRequest request, HttpServletResponse response, Model model) {
        //任务id：查到任务相关信�?�
        long id = NumberUtils.toLong(request.getParameter("id"));
        String config = appDataMigrateCenter.showDataMigrateConf(id);
        model.addAttribute("configList", Arrays.asList(config.split(ConstUtils.NEXT_LINE)));
        return new ModelAndView("migrate/config");
    }
    
    /**
     * 查看�?移进度
     * @return
     */
    @RequestMapping(value = "/process")
    public ModelAndView showProcess(HttpServletRequest request, HttpServletResponse response, Model model) {
        long id = NumberUtils.toLong(request.getParameter("id"));
        Map<RedisMigrateToolConstant, Map<String, Object>> migrateToolStatMap = appDataMigrateCenter.showMiragteToolProcess(id);
        model.addAttribute("migrateToolStatMap", migrateToolStatMap);
        return new ModelAndView("migrate/process");
    }
    
    /**
     * 查看�?移进度
     * @return
     */
    @RequestMapping(value = "/checkData")
    public ModelAndView checkData(HttpServletRequest request, HttpServletResponse response, Model model) {
        long id = NumberUtils.toLong(request.getParameter("id"));
        int nums = 1000 + new Random().nextInt(2000);
        //为了方便，直接传入命令
        CommandResult commandResult = appDataMigrateCenter.sampleCheckData(id, nums);
        String message = commandResult.getResult();
        List<String> checkDataResultList = new ArrayList<String>();
        checkDataResultList.add("一共�?机检验了" + nums + "个key" + ",检查结果如下:");
        String[] lineArr = message.split(ConstUtils.NEXT_LINE);
        for (String line : lineArr) {
            if (StringUtils.isBlank(line)) {
                continue;
            }
            // 行数太多显示会有问题
            if (lineArr.length > 100 && !isUsefulLine(line)) {
                continue;
            }
            //message格�?显示有点问题
            line = line.replace("[0m", "");
            line = line.replace("[31m", "");
            line = line.replace("[33m", "");
            checkDataResultList.add(line.trim());
        }
        model.addAttribute("checkDataResultList", checkDataResultList);
        model.addAttribute("checkDataCommand", commandResult.getCommand());
        return new ModelAndView("migrate/checkData");
    }

    private boolean isUsefulLine(String line) {
        for (String usefulLine : MIGRATE_SAMPLE_USEFUL_LINES) {
            if (line.contains(usefulLine)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查看�?移列表(包�?�历�?�)
     * @return
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Model model, AppDataMigrateSearch appDataMigrateSearch) {
        List<AppDataMigrateStatus> appDataMigrateStatusList = appDataMigrateCenter.search(appDataMigrateSearch);
        model.addAttribute("appDataMigrateStatusList", appDataMigrateStatusList);
        model.addAttribute("appDataMigrateSearch", appDataMigrateSearch);
        return new ModelAndView("migrate/list");
    }
    
    /**
     * 通过应用id获�?��?�用的Redis实例信�?�
     * @return
     */
    @RequestMapping(value = "/appInstanceList")
    public ModelAndView appInstanceList(HttpServletRequest request, HttpServletResponse response, Model model) {
        String appIdStr = request.getParameter("appId");
        long appId = NumberUtils.toLong(appIdStr);
        AppDesc appDesc = appService.getByAppId(appId);
        StringBuffer instances = new StringBuffer();
        List<InstanceInfo> instanceList = appService.getAppInstanceInfo(appId);
        if (CollectionUtils.isNotEmpty(instanceList)) {
            for (int i = 0; i < instanceList.size(); i++) {
                InstanceInfo instanceInfo = instanceList.get(i);
                if (instanceInfo == null) {
                    continue;
                }
                if (instanceInfo.isOffline()) {
                    continue;
                }
                // 如果是sentinel类型的应用�?�出master
                if (TypeUtil.isRedisSentinel(appDesc.getType())) {
                    if (TypeUtil.isRedisSentinel(instanceInfo.getType())) {
                        continue;
                    }
                    if (!redisCenter.isMaster(appId, instanceInfo.getIp(), instanceInfo.getPort())) {
                        continue;
                    }
                }
                instances.append(instanceInfo.getIp() + ":" + instanceInfo.getPort());
                if (i != instanceList.size() - 1) {
                    instances.append(ConstUtils.NEXT_LINE);
                }
            }
        }
        model.addAttribute("instances", instances.toString());
        model.addAttribute("appType", appDesc == null ? -1 : appDesc.getType());
        return new ModelAndView("");
    }
    
}
