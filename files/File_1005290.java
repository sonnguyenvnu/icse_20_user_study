package org.jeecgframework.web.system.controller.core;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.timer.DynamicTask;
import org.jeecgframework.core.util.HttpRequest;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.TimeTaskServiceI;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;


/**   
 * @Title: Controller
 * @Description: 定时任务管�?�
 * @author jueyue
 * @date 2013-09-21 20:47:43
 * @version V1.0   
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/timeTaskController")
public class TimeTaskController extends BaseController {

	@Autowired
	private TimeTaskServiceI timeTaskService;
	@Autowired(required=false)
	private DynamicTask dynamicTask;
	@Autowired
	private SystemService systemService;


	/**
	 * 定时任务管�?�列表 页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "timeTask")
	public ModelAndView timeTask(HttpServletRequest request) {
		return new ModelAndView("system/timetask/timeTaskList");
	}

	/**
	 * easyui AJAX请求数�?�
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TSTimeTaskEntity timeTask,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSTimeTaskEntity.class, dataGrid);
		//查询�?�件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, timeTask, request.getParameterMap());
		this.timeTaskService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除定时任务管�?�
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSTimeTaskEntity timeTask, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		timeTask = systemService.getEntity(TSTimeTaskEntity.class, timeTask.getId());
		if("1".equals(timeTask.getIsStart())){
			message = "任务�?行中�?能删除，请先�?�止任务";
		}else{
			message = "定时任务管�?�删除�?功";
			timeTaskService.delete(timeTask);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);			
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加定时任务管�?�
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TSTimeTaskEntity timeTask, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();

		CronTriggerImpl trigger = new CronTriggerImpl();

		try {
			trigger.setCronExpression(timeTask.getCronExpression());
		} catch (ParseException e) {
			j.setMsg("Cron表达�?错误");
			return j;
		}
		if (StringUtil.isNotEmpty(timeTask.getId())) {
			TSTimeTaskEntity t = timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());
			if ("1".equals(t.getIsStart())) {
				message = "任务�?行中�?�?�编辑，请先�?�止任务";
			}else{
				message = "定时任务管�?�更新�?功";
				try {
					if(!timeTask.getCronExpression().equals(t.getCronExpression())){
						timeTask.setIsEffect("0");
					}
					MyBeanUtils.copyBeanNotNull2Bean(timeTask, t);
					timeTaskService.saveOrUpdate(t);
					systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				} catch (Exception e) {
					e.printStackTrace();
					message = "定时任务管�?�更新失败";
				}
			}
			
		} else {
			message = "定时任务管�?�添加�?功";
			timeTaskService.save(timeTask);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 定时任务管�?�列表页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSTimeTaskEntity timeTask, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(timeTask.getId())) {
			timeTask = timeTaskService.getEntity(TSTimeTaskEntity.class, timeTask.getId());
			req.setAttribute("timeTaskPage", timeTask);
		}
		return new ModelAndView("system/timetask/timeTask");
	}
	
	/**
	 * 更新任务时间使之生效
	 */
	@RequestMapping(params = "updateTime")
	@ResponseBody
	public AjaxJson updateTime(TSTimeTaskEntity timeTask, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		boolean isUpdate = dynamicTask.updateCronExpression(timeTask);
		j.setMsg(isUpdate?"定时任务管�?�更新�?功":"定时任务管�?�更新失败");
		return j;
	}
	
	/**
	 * �?�动或者�?�止任务
	 */
	@RequestMapping(params = "startOrStopTask")
	@ResponseBody
	public AjaxJson startOrStopTask(TSTimeTaskEntity timeTask, HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		boolean isStart = timeTask.getIsStart().equals("1");
		timeTask = timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());		
		boolean isSuccess = false;
		
		if ("0".equals(timeTask.getIsEffect())) {
			j.setMsg("该任务为�?用状�?，请解除�?用�?��?新�?�动");
			return j;
		}
		if (isStart && "1".equals(timeTask.getIsStart())) {
			j.setMsg("该任务当�?已�?�?�动，请�?�止�?��?试");
			return j;
		}
		if (!isStart && "0".equals(timeTask.getIsStart())) {
			j.setMsg("该任务当�?已�?�?�止，�?�?�?作");
			return j;
		}
		//String serverIp = InetAddress.getLocalHost().getHostAddress();
		List<String> ipList = IpUtil.getLocalIPList();
		String runServerIp = timeTask.getRunServerIp();

		if((ipList.contains(runServerIp) || StringUtil.isEmpty(runServerIp) || "本地".equals(runServerIp)) && (runServerIp.equals(timeTask.getRunServer()))){//当�?�?务器IP匹�?�?功

			isSuccess = dynamicTask.startOrStop(timeTask ,isStart);	
		}else{
			try {
				String url = "http://"+timeTask.getRunServer()+"/timeTaskController.do?remoteTask";//spring-mvc.xml
				String param = "id="+timeTask.getId()+"&isStart="+(isStart ? "1" : "0");
				JSONObject json = HttpRequest.sendPost(url, param);
				isSuccess = json.getBooleanValue("success");
			} catch (Exception e) {
				j.setMsg("远程主机‘"+timeTask.getRunServer()+"’�?应超时");
				return j;
			}
		}		
		j.setMsg(isSuccess?"定时任务管�?�更新�?功":"定时任务管�?�更新失败");
		return j;
	}
	
	
	/**
	 * 远程�?�动或者�?�止任务
	 */
	@RequestMapping(params = "remoteTask")
	@ResponseBody
	public JSONObject remoteTask(TSTimeTaskEntity timeTask, HttpServletRequest request) {
		
		JSONObject json = new JSONObject();
		boolean isStart = timeTask.getIsStart().equals("1");
		timeTask = timeTaskService.get(TSTimeTaskEntity.class, timeTask.getId());		
		boolean isSuccess = true;
		
		if ("0".equals(timeTask.getIsEffect())) {
			isSuccess = false;
		}else if (isStart && "1".equals(timeTask.getIsStart())) {
			isSuccess = false;
		}else if (!isStart && "0".equals(timeTask.getIsStart())) {
			isSuccess = false;
		}else{

			try {
				isSuccess = dynamicTask.startOrStop(timeTask ,isStart);
			} catch (Exception e) {
				e.printStackTrace();
				json.put("success", false);
				return json;
			}

		}
		json.put("success", isSuccess);
		return json;
	}
}
