package com.sohu.cache.web.controller;

import com.sohu.cache.web.enums.RedisOperateEnum;
import com.sohu.cache.constant.AppCheckEnum;
import com.sohu.cache.constant.ClusterOperateResult;
import com.sohu.cache.constant.DataFormatCheckResult;
import com.sohu.cache.constant.ErrorMessageEnum;
import com.sohu.cache.constant.HorizontalResult;
import com.sohu.cache.dao.InstanceReshardProcessDao;
import com.sohu.cache.entity.*;
import com.sohu.cache.machine.MachineCenter;
import com.sohu.cache.redis.RedisCenter;
import com.sohu.cache.redis.RedisDeployCenter;
import com.sohu.cache.stats.app.AppDailyDataCenter;
import com.sohu.cache.stats.app.AppDeployCenter;
import com.sohu.cache.stats.instance.InstanceDeployCenter;
import com.sohu.cache.util.ConstUtils;
import com.sohu.cache.util.TypeUtil;
import com.sohu.cache.web.enums.SuccessEnum;
import com.sohu.cache.web.util.AppEmailUtil;
import com.sohu.cache.web.util.DateUtil;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.util.*;

/**
 * 应用�?��?�管�?�
 *
 * @author leifu
 * @Time 2014年7月3日
 */
@Controller
@RequestMapping("manage/app")
public class AppManageController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(AppManageController.class);

	@Resource(name = "machineCenter")
	private MachineCenter machineCenter;

	@Resource(name = "appEmailUtil")
	private AppEmailUtil appEmailUtil;

	@Resource(name = "appDeployCenter")
	private AppDeployCenter appDeployCenter;

	@Resource(name = "redisCenter")
	private RedisCenter redisCenter;

	@Resource(name = "redisDeployCenter")
	private RedisDeployCenter redisDeployCenter;
	
	@Resource(name = "instanceDeployCenter")
	private InstanceDeployCenter instanceDeployCenter;

	@Resource(name = "appDailyDataCenter")
    private AppDailyDataCenter appDailyDataCenter;
	
    @Resource(name = "instanceReshardProcessDao")
	private InstanceReshardProcessDao instanceReshardProcessDao;
	
	@RequestMapping("/appDaily")
    public ModelAndView appDaily(HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
	    AppUser userInfo = getUserInfo(request);
        logger.warn("user {} want to send appdaily", userInfo.getName());
        if (ConstUtils.SUPER_MANAGER.contains(userInfo.getName())) {
            Date startDate;
            Date endDate;
            String startDateParam = request.getParameter("startDate");
            String endDateParam = request.getParameter("endDate");
            if (StringUtils.isBlank(startDateParam) || StringUtils.isBlank(endDateParam)) {
                endDate = new Date();
                startDate = DateUtils.addDays(endDate, -1);
            } else {
                startDate = DateUtil.parseYYYY_MM_dd(startDateParam);
                endDate = DateUtil.parseYYYY_MM_dd(endDateParam);
            }
            long appId = NumberUtils.toLong(request.getParameter("appId"));
            if (appId > 0) {
                appDailyDataCenter.sendAppDailyEmail(appId, startDate, endDate);
            } else {
                appDailyDataCenter.sendAppDailyEmail();
            }
            model.addAttribute("msg", "success!");
        } else {
            model.addAttribute("msg", "no power!");
        }
        return new ModelAndView("");
    }
	
	/**
	 * 审核列表
	 * 
	 * @param status 审核状�?
	 * @param type 申请类型
	 */
	@RequestMapping(value = "/auditList")
	public ModelAndView doAppAuditList(HttpServletRequest request,HttpServletResponse response, Model model,
	        Integer status, Integer type) {
	    //获�?�审核列表
		List<AppAudit> list = appService.getAppAudits(status, type);

		model.addAttribute("list", list);
		model.addAttribute("status", status);
		model.addAttribute("type", type);
		model.addAttribute("checkActive", SuccessEnum.SUCCESS.value());

		return new ModelAndView("manage/appAudit/list");
	}

	/**
	 * 处�?�应用�?置修改
	 * 
	 * @param appAuditId 审批id
	 */
	@RequestMapping(value = "/initAppConfigChange")
	public ModelAndView doInitAppConfigChange(HttpServletRequest request,
			HttpServletResponse response, Model model, Long appAuditId) {
		// 申请原因
		AppAudit appAudit = appService.getAppAuditById(appAuditId);
		model.addAttribute("appAudit", appAudit);

		// 用第一个�?�数存实例id
		Long instanceId = NumberUtils.toLong(appAudit.getParam1());
		Map<String, String> redisConfigList = redisCenter.getRedisConfigList(instanceId.intValue());
		model.addAttribute("redisConfigList", redisConfigList);
		model.addAttribute("instanceId", instanceId);

		// 实例列表
		List<InstanceInfo> instanceList = appService.getAppInstanceInfo(appAudit.getAppId());
		model.addAttribute("instanceList", instanceList);
		model.addAttribute("appId", appAudit.getAppId());
		model.addAttribute("appAuditId", appAuditId);

		// 修改�?置的键值对
		model.addAttribute("appConfigKey", appAudit.getParam2());
		model.addAttribute("appConfigValue", appAudit.getParam3());

		return new ModelAndView("manage/appAudit/initAppConfigChange");
	}

	/**
	 * 添加应用�?置修改
	 * 
	 * @param appId 应用id
	 * @param appConfigKey �?置项
	 * @param appConfigValue �?置值
	 * @param appAuditId 审批id
	 */
	@RequestMapping(value = "/addAppConfigChange")
	public ModelAndView doAddAppConfigChange(HttpServletRequest request,
			HttpServletResponse response, Model model, Long appId,
			String appConfigKey, String appConfigValue, Long appAuditId) {
	    AppUser appUser = getUserInfo(request);
        logger.warn("user {} change appConfig:appId={};key={};value={},appAuditId:{}", appUser.getName(), appId, appConfigKey, appConfigValue, appAuditId);
        boolean isModify = false;
        if (appId != null && appAuditId != null && StringUtils.isNotBlank(appConfigKey) && StringUtils.isNotBlank(appConfigValue)) {
			try {
				isModify = appDeployCenter.modifyAppConfig(appId, appAuditId, appConfigKey, appConfigValue);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
        logger.warn("user {} change appConfig:appId={};key={};value={},appAuditId:{},result is:{}", appUser.getName(), appId, appConfigKey, appConfigValue, appAuditId, isModify);
		return new ModelAndView("redirect:/manage/app/auditList");
	}

	/**
	 * �?始化水平扩容申请
	 */
	@RequestMapping(value = "/initHorizontalScaleApply")
	public ModelAndView doInitHorizontalScaleApply(HttpServletRequest request, HttpServletResponse response, Model model, Long appAuditId) {
		AppAudit appAudit = appService.getAppAuditById(appAuditId);
		model.addAttribute("appAudit", appAudit);
		model.addAttribute("appId", appAudit.getAppId());
		return new ModelAndView("manage/appAudit/initHorizontalScaleApply");
	}
	
	

    /**
     * 添加水平扩容节点
     * 
     * @return
     */
    @RequestMapping(value = "/addHorizontalNodes")
    public ModelAndView doAddHorizontalNodes(HttpServletRequest request,
            HttpServletResponse response, Model model, String masterSizeSlave,
            Long appAuditId) {
        AppUser appUser = getUserInfo(request);
        logger.warn("user {} addHorizontalNodes:{}", appUser.getName(), masterSizeSlave);
        boolean isAdd = false;
        AppAudit appAudit = appService.getAppAuditById(appAuditId);
        // 解�?�?置
        String[] configArr = masterSizeSlave.split(ConstUtils.COLON);
        String masterHost = configArr[0];
        String memSize = configArr[1];
        int memSizeInt = NumberUtils.toInt(memSize);
        String slaveHost = null;
        if (configArr.length >= 3) {
            slaveHost = configArr[2];
        }
        try {
            isAdd = appDeployCenter.addHorizontalNodes(appAudit.getAppId(), masterHost, slaveHost, memSizeInt);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.warn("addAppClusterSharding:{}, result is {}", masterSizeSlave, isAdd);
        model.addAttribute("status", isAdd ? 1 : 0);
        return new ModelAndView("");
    }

    /**
     * 检测水平扩容节点
     * @param masterSizeSlave
     * @param appAuditId
     * @return
     */
	@RequestMapping(value = "/checkHorizontalNodes")
	public ModelAndView doCheckHorizontalNodes(HttpServletRequest request,
			HttpServletResponse response, Model model, String masterSizeSlave,
			Long appAuditId) {
	    DataFormatCheckResult dataFormatCheckResult = null;
        try {
            dataFormatCheckResult = appDeployCenter.checkHorizontalNodes(appAuditId, masterSizeSlave);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            dataFormatCheckResult = DataFormatCheckResult.fail(ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
        }
        model.addAttribute("status", dataFormatCheckResult.getStatus());
        model.addAttribute("message", dataFormatCheckResult.getMessage());
        return new ModelAndView("");
	}

	/**
	 * 水平扩容�?始化
	 * 
	 * @param appAuditId
	 */
	@RequestMapping(value = "/handleHorizontalScale")
	public ModelAndView doHandleHorizontalScale(HttpServletRequest request,
			HttpServletResponse response, Model model, Long appAuditId) {
		// 1. 审批
		AppAudit appAudit = appService.getAppAuditById(appAuditId);
		model.addAttribute("appAudit", appAudit);
		model.addAttribute("appId", appAudit.getAppId());

		// 2. 进度
		List<InstanceReshardProcess> instanceReshardProcessList = instanceReshardProcessDao.getByAuditId(appAudit.getId());
		model.addAttribute("instanceReshardProcessList", instanceReshardProcessList);

		// 3. 实例列表和统计
		fillAppInstanceStats(appAudit.getAppId(), model);
		// 4. 实例所在机器信�?�
		fillAppMachineStat(appAudit.getAppId(), model);

		return new ModelAndView("manage/appAudit/handleHorizontalScale");
	}

	/**
	 * 显示reshard进度
	 */
	@RequestMapping(value = "/showReshardProcess")
	public ModelAndView doShowReshardProcess(HttpServletRequest request, HttpServletResponse response, Model model) {
	    long auditId = NumberUtils.toLong(request.getParameter("auditId"));
        List<InstanceReshardProcess> instanceReshardProcessList = instanceReshardProcessDao.getByAuditId(auditId);
        write(response, JSONArray.fromObject(instanceReshardProcessList).toString());
        return null;
	}

//	/**
//     * 把Map组装�?JsonArray
//     * 
//     * @param appScaleProcessMap
//     * @return
//     */
//    private String filterMapToJsonArray(ConcurrentMap<Long, ReshardProcess> appScaleProcessMap) {
//        if (MapUtils.isEmpty(appScaleProcessMap)) {
//            return "[]";
//        }
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        for (Entry<Long, ReshardProcess> entry : appScaleProcessMap.entrySet()) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("appId", entry.getKey());
//            map.put("reshardSlot", entry.getValue().getReshardSlot());
//            map.put("totalSlot", entry.getValue().getTotalSlot());
//            map.put("status", entry.getValue().getStatus());
//            list.add(map);
//        }
//        return JSONArray.fromObject(list).toString();
//    }

	/**
	 * 水平扩容�?置检查
	 * @param sourceId �?实例ID
	 * @param targetId 目标实例ID
	 * @param startSlot 开始slot
	 * @param endSlot 结�?�slot
	 * @param appId 应用id
	 * @param appAuditId 审批id
	 * @return
	 */
	@RequestMapping(value = "/checkHorizontalScale")
	public ModelAndView doCheckHorizontalScale(HttpServletRequest request, HttpServletResponse response, Model model,
			long sourceId, long targetId, int startSlot, int endSlot, long appId, long appAuditId, int migrateType) {
		HorizontalResult horizontalResult = appDeployCenter.checkHorizontal(appId, appAuditId, sourceId, targetId,
				startSlot, endSlot, migrateType);
		model.addAttribute("status", horizontalResult.getStatus());
		model.addAttribute("message", horizontalResult.getMessage());
		return new ModelAndView("");
	}
	
	/**
	 * 开始水平扩容
	 * @param sourceId �?实例ID
	 * @param targetId 目标实例ID
	 * @param startSlot 开始slot
	 * @param endSlot 结�?�slot
	 * @param appId 应用id
	 * @param appAuditId 审批id
	 * @return
	 */
	@RequestMapping(value = "/startHorizontalScale")
	public ModelAndView doStartHorizontalScale(HttpServletRequest request, HttpServletResponse response, Model model,
			long sourceId, long targetId, int startSlot, int endSlot, long appId, long appAuditId, int migrateType) {
		AppUser appUser = getUserInfo(request);
		logger.warn("user {} horizontalScaleApply appId {} appAuditId {} sourceId {} targetId {} startSlot {} endSlot {}",
				appUser.getName(), appId, appAuditId, sourceId, targetId, startSlot, endSlot);
		HorizontalResult horizontalResult = appDeployCenter.startHorizontal(appId, appAuditId, sourceId, targetId,
				startSlot, endSlot, migrateType);
        model.addAttribute("status", horizontalResult.getStatus());
		model.addAttribute("message", horizontalResult.getMessage());
		return new ModelAndView("");
	}
	
	/**
	 * �?试水平扩容
	 * @param instanceReshardProcessId
	 * @return
	 */
    @RequestMapping(value = "/retryHorizontalScale")
    public ModelAndView retryHorizontalScale(HttpServletRequest request, HttpServletResponse response, Model model, int instanceReshardProcessId) {
        AppUser appUser = getUserInfo(request);
        logger.warn("user {} retryHorizontalScale id {}", appUser.getName(), instanceReshardProcessId);
        HorizontalResult horizontalResult = appDeployCenter.retryHorizontal(instanceReshardProcessId);
        model.addAttribute("status", horizontalResult.getStatus());
        model.addAttribute("message", horizontalResult.getMessage());
        return new ModelAndView("");
    }

	/**
	 * 处�?�应用扩容
	 * 
	 * @param appAuditId 审批id
	 */
	@RequestMapping(value = "/initAppScaleApply")
	public ModelAndView doInitAppScaleApply(HttpServletRequest request, HttpServletResponse response, Model model, Long appAuditId) {
		// 申请原因
		AppAudit appAudit = appService.getAppAuditById(appAuditId);
		model.addAttribute("appAudit", appAudit);

		// 实例列表和统计
		fillAppInstanceStats(appAudit.getAppId(), model);
		// 实例所在机器信�?�
        fillAppMachineStat(appAudit.getAppId(), model);

		long appId = appAudit.getAppId();
		AppDesc appDesc = appService.getByAppId(appId);
        model.addAttribute("appAuditId", appAuditId);
		model.addAttribute("appId", appAudit.getAppId());
        model.addAttribute("appDesc", appDesc);
		
		return new ModelAndView("manage/appAudit/initAppScaleApply");
	}

	/**
	 * 添加扩容�?置
	 * 
	 * @param appScaleText 扩容�?置
	 * @param appAuditId 审批id
	 */
	@RequestMapping(value = "/addAppScaleApply")
	public ModelAndView doAddAppScaleApply(HttpServletRequest request,
			HttpServletResponse response, Model model, String appScaleText,
			Long appAuditId, Long appId) {
	    AppUser appUser = getUserInfo(request);
        logger.error("user {} appScaleApplay : appScaleText={},appAuditId:{}", appUser.getName(), appScaleText, appAuditId);
        boolean isSuccess = false;
		if (appAuditId != null && StringUtils.isNotBlank(appScaleText)) {
			int mem = NumberUtils.toInt(appScaleText, 0);
			try {
			    isSuccess = appDeployCenter.verticalExpansion(appId, appAuditId, mem);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			logger.error("appScaleApplay error param: appScaleText={},appAuditId:{}", appScaleText, appAuditId);
		}
        logger.error("user {} appScaleApplay: appScaleText={},appAuditId:{}, result is {}", appUser.getName(), appScaleText, appAuditId, isSuccess);
		return new ModelAndView("redirect:/manage/app/auditList");
	}

	/**
	 * �?始化部署应用
	 * 
	 * @param appAuditId 审批id
	 * @return
	 */
	@RequestMapping(value = "/initAppDeploy")
	public ModelAndView doInitAppDeploy(HttpServletRequest request, HttpServletResponse response, Model model, Long appAuditId) {
		// 申请原因
		AppAudit appAudit = appService.getAppAuditById(appAuditId);
		model.addAttribute("appAudit", appAudit);

		// 机器列表
		List<MachineStats> machineList = machineCenter.getAllMachineStats();
		model.addAttribute("machineList", machineList);
		model.addAttribute("appAuditId", appAuditId);
		model.addAttribute("appId", appAudit.getAppId());
		model.addAttribute("appDesc", appService.getByAppId(appAudit.getAppId()));

		return new ModelAndView("manage/appAudit/initAppDeploy");
	}
	
	/**
     * 应用部署�?置检查
     * @return
     */
    @RequestMapping(value = "/appDeployCheck")
    public ModelAndView doAppDeployCheck(HttpServletRequest request, HttpServletResponse response, Model model, String appDeployText,
            Long appAuditId) {
        DataFormatCheckResult dataFormatCheckResult = null;
        try {
            dataFormatCheckResult = appDeployCenter.checkAppDeployDetail(appAuditId, appDeployText);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            dataFormatCheckResult = DataFormatCheckResult.fail(ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
        }
        model.addAttribute("status", dataFormatCheckResult.getStatus());
        model.addAttribute("message", dataFormatCheckResult.getMessage());
        return new ModelAndView("");
    }

	/**
	 * 添加应用部署
	 * 
	 * @param appDeployText 部署�?置
	 * @param appAuditId 审批id
	 * @return
	 */
	@RequestMapping(value = "/addAppDeploy")
	public ModelAndView doAddAppDeploy(HttpServletRequest request,
			HttpServletResponse response, Model model, String appDeployText,
			Long appAuditId) {
	    AppUser appUser = getUserInfo(request);
        logger.warn("user {} appDeploy: appDeployText={},appAuditId:{}", appUser.getName(), appDeployText, appAuditId);
        boolean isSuccess = false;
	    if (appAuditId != null && StringUtils.isNotBlank(appDeployText)) {
			String[] appDetails = appDeployText.split("\n");
			// 部署service
			isSuccess = appDeployCenter.allocateResourceApp(appAuditId, Arrays.asList(appDetails), getUserInfo(request));
		} else {
			logger.error("appDeploy error param: appDeployText={},appAuditId:{}", appDeployText, appAuditId);
		}
        logger.warn("user {} appDeploy: appDeployText={},appAuditId:{}, result is {}", appUser.getName(), appDeployText, appAuditId, isSuccess);
        model.addAttribute("status", isSuccess ? 1 : 0);
        return new ModelAndView("");
	}

	/**
	 * 通过,获�?�驳回申请
	 * 
	 * @param status 审批状�?
	 * @param appAuditId 审批id
	 * @param refuseReason 应用id
	 * @return
	 */
	@RequestMapping(value = "/addAuditStatus")
	public ModelAndView doAddAuditStatus(HttpServletRequest request, HttpServletResponse response, Model model, Integer status, Long appAuditId, String refuseReason) {
	    AppUser appUser = getUserInfo(request);
        logger.warn("user {} addAuditStatus: status={},appAuditId:{},refuseReason:{}", appUser.getName(), status, appAuditId, refuseReason);
	    AppAudit appAudit = appService.getAppAuditById(appAuditId);
		Long appId = appAudit.getAppId();
		// 通过或者驳回并记录日志
		appService.updateAppAuditStatus(appAuditId, appId, status, getUserInfo(request));

		// 记录驳回原因
		if (AppCheckEnum.APP_REJECT.value().equals(status)) {
			appAudit.setRefuseReason(refuseReason);
			appService.updateRefuseReason(appAudit, getUserInfo(request));
		}

		// �?�邮件统计
		if (AppCheckEnum.APP_PASS.value().equals(status) || AppCheckEnum.APP_REJECT.value().equals(status)) {
			AppDesc appDesc = appService.getByAppId(appId);
			appEmailUtil.noticeAppResult(appDesc, appService.getAppAuditById(appAuditId));
		}

		// 批准�?功直接跳转
		if (AppCheckEnum.APP_PASS.value().equals(status)) {
			return new ModelAndView("redirect:/manage/app/auditList");
		}

		write(response, String.valueOf(SuccessEnum.SUCCESS.value()));
		return null;
	}

	/**
	 * 下线应用
	 * 
	 * @param appId
	 * @return
	 */
	@RequestMapping(value = "/offLine")
	public ModelAndView offLineApp(HttpServletRequest request,
			HttpServletResponse response, Model model, Long appId) {
		AppUser userInfo = getUserInfo(request);
		logger.warn("user {} hope to offline appId: {}", userInfo.getName(), appId);
		if (ConstUtils.SUPER_MANAGER.contains(userInfo.getName())) {
			boolean result = appDeployCenter.offLineApp(appId);
			model.addAttribute("appId", appId);
			model.addAttribute("result", result);
			if (result) {
				model.addAttribute("msg", "�?作�?功");
			} else {
				model.addAttribute("msg", "�?作失败");
			}
		    logger.warn("user {} offline appId: {}, result is {}", userInfo.getName(), appId, result);
		    appEmailUtil.noticeOfflineApp(userInfo, appId, result);
		} else {
		    logger.warn("user {} hope to offline appId: {}, hasn't provilege", userInfo.getName(), appId);
			model.addAttribute("result", false);
			model.addAttribute("msg", "�?��?�?足");
	        appEmailUtil.noticeOfflineApp(userInfo, appId, false);
		}
		return new ModelAndView();
	}

	/**
	 * 实例机器信�?�
	 * @param appId
	 * @param model
	 */
	private void fillAppMachineStat(Long appId, Model model){
        List<InstanceInfo> instanceList = appService.getAppInstanceInfo(appId);
        
        Map<String, MachineStats> machineStatsMap = new HashMap<String, MachineStats>();
        Map<String, Long> machineCanUseMem = new HashMap<String, Long>();
        
        for (InstanceInfo instanceInfo : instanceList) {
            if (TypeUtil.isRedisSentinel(instanceInfo.getType())) {
                continue;
            }
            String ip = instanceInfo.getIp();
            if (machineStatsMap.containsKey(ip)) {
                continue;
            }
            MachineStats machineStats = machineCenter.getMachineMemoryDetail(ip);
            machineStatsMap.put(ip, machineStats);
            machineCanUseMem.put(ip, machineStats.getMachineMemInfo().getLockedMem());
        }
        model.addAttribute("machineCanUseMem", machineCanUseMem);
        model.addAttribute("machineStatsMap", machineStatsMap);
	}
	
	
	/**
	 * 应用�?维
	 * @param appId
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model, Long appId) {
		model.addAttribute("appId", appId);
		return new ModelAndView("manage/appOps/appOpsIndex");
	}

	/**
	 * 应用机器�?维
	 * @param appId
	 */
	@RequestMapping("/machine")
	public ModelAndView appMachine(HttpServletRequest request, HttpServletResponse response, Model model, Long appId) {
		if (appId != null && appId > 0) {
			List<MachineStats> appMachineList = appService.getAppMachineDetail(appId);
			model.addAttribute("appMachineList", appMachineList);
			AppDesc appDesc = appService.getByAppId(appId);
			model.addAttribute("appDesc", appDesc);
		}
		return new ModelAndView("manage/appOps/appMachine");
	}

	/**
	 * 应用实例�?维
	 * @param appId
	 */
	@RequestMapping("/instance")
	public ModelAndView appInstance(HttpServletRequest request, HttpServletResponse response, Model model, Long appId) {
		if (appId != null && appId > 0) {
			AppDesc appDesc = appService.getByAppId(appId);
			model.addAttribute("appDesc", appDesc);
			//实例信�?�和统计
			fillAppInstanceStats(appId, model);
			
			//�?�有cluster类型�?需�?计算slot相关
            if (TypeUtil.isRedisCluster(appDesc.getType())) {
                // 计算丢失的slot区间
                Map<String,String> lossSlotsSegmentMap = redisCenter.getClusterLossSlots(appId);
                model.addAttribute("lossSlotsSegmentMap", lossSlotsSegmentMap);
            }
		}
		return new ModelAndView("manage/appOps/appInstance");
	}

	/**
	 * 应用详细信�?�和�?��?申请记录
	 * @param appId
	 */
	@RequestMapping("/detail")
	public ModelAndView appInfoAndAudit(HttpServletRequest request, HttpServletResponse response, Model model, Long appId) {
		if (appId != null && appId > 0) {
			List<AppAudit> appAuditList = appService.getAppAuditListByAppId(appId);
			AppDesc appDesc = appService.getByAppId(appId);
			model.addAttribute("appAuditList", appAuditList);
			model.addAttribute("appDesc", appDesc);
		}
		return new ModelAndView("manage/appOps/appInfoAndAudit");
	}
	
	/**
     * redisCluster节点删除: forget + shutdown
     * 
     * @param appId 应用id
     * @param forgetInstanceId 需�?被forget的节点
     * @return
     */
    @RequestMapping("/clusterDelNode")
    public ModelAndView clusterDelNode(HttpServletRequest request, HttpServletResponse response, Model model, Long appId,
            int delNodeInstanceId) {
        AppUser appUser = getUserInfo(request);
        logger.warn("user {}, clusterForget: appId:{}, instanceId:{}", appUser.getName(), appId, delNodeInstanceId);
        // 检测forget�?�件
        ClusterOperateResult checkClusterForgetResult = null;
        try {
            checkClusterForgetResult = redisDeployCenter.checkClusterForget(appId, delNodeInstanceId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (checkClusterForgetResult == null || !checkClusterForgetResult.isSuccess()) {
            model.addAttribute("success", checkClusterForgetResult.getStatus());
            model.addAttribute("message", checkClusterForgetResult.getMessage());
            return new ModelAndView("");
        }
        
        // 执行delnode:forget + shutdown
        ClusterOperateResult delNodeResult = null;
        try {
            delNodeResult = redisDeployCenter.delNode(appId, delNodeInstanceId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        model.addAttribute("success", delNodeResult.getStatus());
        model.addAttribute("message", delNodeResult.getMessage());
        logger.warn("user {}, clusterForget: appId:{}, instanceId:{}, result is {}", appUser.getName(), appId, delNodeInstanceId, delNodeResult.getStatus());
        
        return new ModelAndView("");
        
    }

	/**
	 * redisCluster从节点failover
	 * 
	 * @param appId 应用id
	 * @param slaveInstanceId 从节点instanceId
	 * @return
	 */
	@RequestMapping("/clusterSlaveFailOver")
	public void clusterSlaveFailOver(HttpServletRequest request, HttpServletResponse response, Model model, Long appId,
			int slaveInstanceId) {
		boolean success = false;
		String failoverParam = request.getParameter("failoverParam");
		logger.warn("clusterSlaveFailOver: appId:{}, slaveInstanceId:{}, failoverParam:{}", appId, slaveInstanceId, failoverParam);
		if (appId != null && appId > 0 && slaveInstanceId > 0) {
			try {
				success = redisDeployCenter.clusterFailover(appId, slaveInstanceId, failoverParam);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			logger.error("error param clusterSlaveFailOver: appId:{}, slaveInstanceId:{}, failoverParam:{}", appId, slaveInstanceId, failoverParam);
		}
	    logger.warn("clusterSlaveFailOver: appId:{}, slaveInstanceId:{}, failoverParam:{}, result is {}", appId, slaveInstanceId, failoverParam, success);
		write(response, String.valueOf(success == true ? SuccessEnum.SUCCESS.value() : SuccessEnum.FAIL.value()));
	}

	/**
	 * 添加slave节点
	 * 
	 * @param appId
	 * @param masterInstanceId
	 * @param slaveHost
	 * @return
	 */
    @RequestMapping(value = "/addSlave")
    public void addSlave(HttpServletRequest request, HttpServletResponse response, Model model, long appId,
            int masterInstanceId, String slaveHost) {
        AppUser appUser = getUserInfo(request);
        logger.warn("user {} addSlave: appId:{},masterInstanceId:{},slaveHost:{}", appUser.getName(), appId, masterInstanceId, slaveHost);
        boolean success = false;
        if (appId > 0 && StringUtils.isNotBlank(slaveHost) && masterInstanceId > 0) {
            try {
                success = redisDeployCenter.addSlave(appId, masterInstanceId, slaveHost);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } 
        logger.warn("user {} addSlave: appId:{},masterInstanceId:{},slaveHost:{} result is {}", appUser.getName(), appId, masterInstanceId, slaveHost, success);
        write(response, String.valueOf(success == true ? SuccessEnum.SUCCESS.value() : SuccessEnum.FAIL.value()));
    }

    /**
     * 添加sentinel节点
     * @param appId
     * @param sentinelHost
     * @return
     */
	@RequestMapping(value = "/addSentinel")
	public void addSentinel(HttpServletRequest request, HttpServletResponse response, Model model, long appId, String sentinelHost) {
        AppUser appUser = getUserInfo(request);
		logger.warn("user {} addSentinel: appId:{}, sentinelHost:{}", appUser.getName(), appId, sentinelHost);
	    boolean success = false;
		if (appId > 0 && StringUtils.isNotBlank(sentinelHost)) {
			try {
				success = redisDeployCenter.addSentinel(appId, sentinelHost);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	    logger.warn("user {} addSentinel: appId:{}, sentinelHost:{} result is {}", appUser.getName(), appId, sentinelHost, success);
		write(response, String.valueOf(success == true ? SuccessEnum.SUCCESS.value() : SuccessEnum.FAIL.value()));
	}
	
	/**
	 * 为失�?�的slot添加master节点
	 * @param appId
	 * @param sentinelHost
	 */
	@RequestMapping(value = "/addFailSlotsMaster")
    public void addFailSlotsMaster(HttpServletRequest request, HttpServletResponse response, Model model, long appId, String failSlotsMasterHost, int instanceId) {
        AppUser appUser = getUserInfo(request);
        logger.warn("user {} addFailSlotsMaster: appId:{}, instanceId {}, newMasterHost:{}", appUser.getName(), appId, instanceId, failSlotsMasterHost);
        RedisOperateEnum redisOperateEnum = RedisOperateEnum.FAIL;
        if (appId > 0 && StringUtils.isNotBlank(failSlotsMasterHost)) {
            try {
                redisOperateEnum = redisDeployCenter.addSlotsFailMaster(appId, instanceId, failSlotsMasterHost);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        logger.warn("user {} addFailSlotsMaster: appId:{}, instanceId {}, newMasterHost:{} result is {}", appUser.getName(), appId, instanceId, failSlotsMasterHost, redisOperateEnum.getValue());
        write(response, String.valueOf(redisOperateEnum.getValue()));
    }

	
	
	/**
	 * sentinelFailOver�?作
	 * 
	 * @param appId
	 * @return
	 */
    @RequestMapping("/sentinelFailOver")
	public void sentinelFailOver(HttpServletRequest request, HttpServletResponse response, Model model, long appId) {
        AppUser appUser = getUserInfo(request);
		logger.warn("user {} sentinelFailOver, appId:{}", appUser.getName(), appId);
	    boolean success = false;
		if (appId > 0) {
			try {
				success = redisDeployCenter.sentinelFailover(appId);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			logger.error("error param, sentinelFailOver: appId:{}", appId);
		}
	    logger.warn("user {} sentinelFailOver, appId:{}, result is {}", appUser.getName(), appId, success);
		write(response, String.valueOf(success == true ? SuccessEnum.SUCCESS.value() : SuccessEnum.FAIL.value()));
	}
    
    /**
     * 应用�?�?性级别
     */
    @RequestMapping(value = "/updateAppImportantLevel")
    public ModelAndView doUpdateAppImportantLevel(HttpServletRequest request, HttpServletResponse response, Model model) {
        long appId = NumberUtils.toLong(request.getParameter("appId"));
        int importantLevel = NumberUtils.toInt(request.getParameter("importantLevel"));
        SuccessEnum successEnum = SuccessEnum.FAIL;
        if (appId > 0 && importantLevel >= 0) {
            try {
                AppDesc appDesc = appService.getByAppId(appId);
                appDesc.setImportantLevel(importantLevel);
                appService.update(appDesc);
                successEnum = SuccessEnum.SUCCESS;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        model.addAttribute("status", successEnum.value());
        return new ModelAndView("");
    }
    
    /**
     * 更新应用密�?
     */
    @RequestMapping(value = "/updateAppPassword")
    public ModelAndView doUpdateAppPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
        long appId = NumberUtils.toLong(request.getParameter("appId"));
        String password = request.getParameter("password");
        SuccessEnum successEnum = SuccessEnum.FAIL;
        if (appId > 0) {
            try {
                AppDesc appDesc = appService.getByAppId(appId);
                appDesc.setPassword(password);
                appService.update(appDesc);
                successEnum = SuccessEnum.SUCCESS;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        model.addAttribute("status", successEnum.value());
        return new ModelAndView("");
    }

}
