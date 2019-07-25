package org.jeecgframework.web.cgform.controller.enhance;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;
import org.jeecgframework.web.cgform.service.button.CgformButtonServiceI;
import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJavaServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



/**   
 * @Title: Controller
 * @Description: cgform_enhance_java
 * @author onlineGenerator
 * @date 2015-06-29 13:48:27
 * @version V1.0   
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/cgformEnhanceJavaController")
public class CgformEnhanceJavaController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CgformEnhanceJavaController.class);

	@Autowired
	private CgformEnhanceJavaServiceI cgformEnhanceJavaService;
	@Autowired
	private CgformButtonServiceI cgformButtonService;
	@Autowired
	private SystemService systemService;


	/**
	 * 列表 页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "cgformEnhanceJava")
	public ModelAndView cgformEnhanceJava(HttpServletRequest request) {
		String formId = request.getParameter("formId");
		String tableName = request.getParameter("tableName");
		request.setAttribute("formId", formId);
		request.setAttribute("tableName", tableName);
		return new ModelAndView("jeecg/cgform/enhance/cgformEnhanceJavaList");
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
	public void datagrid(CgformEnhanceJavaEntity cgformEnhanceJava,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CgformEnhanceJavaEntity.class, dataGrid);
		//查询�?�件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cgformEnhanceJava, request.getParameterMap());
		try{
		//自定义追加查询�?�件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.cgformEnhanceJavaService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除cgform_enhance_java
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CgformEnhanceJavaEntity cgformEnhanceJava, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		cgformEnhanceJava = systemService.getEntity(CgformEnhanceJavaEntity.class, cgformEnhanceJava.getId());
		message = "删除�?功";
		try{
			cgformEnhanceJavaService.delete(cgformEnhanceJava);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批�?删除cgform_enhance_java
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "删除�?功";
		try{
			for(String id:ids.split(",")){
				CgformEnhanceJavaEntity cgformEnhanceJava = systemService.getEntity(CgformEnhanceJavaEntity.class, 
				id
				);
				cgformEnhanceJavaService.delete(cgformEnhanceJava);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 
	 /**
	 * 查找数�?�
	 * 
	 * @return
	 */
	@RequestMapping(params = "doCgformEnhanceJava")
	@ResponseBody  
	public AjaxJson doCgformEnhanceJava(CgformEnhanceJavaEntity cgformEnhanceJavaEntity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		CgformEnhanceJavaEntity doCgformEnhanceJava = cgformEnhanceJavaService.getCgformEnhanceJavaEntityByCodeFormId(cgformEnhanceJavaEntity.getButtonCode(), cgformEnhanceJavaEntity.getFormId(), cgformEnhanceJavaEntity.getEvent());

		if(doCgformEnhanceJava!=null){
			j.setObj(doCgformEnhanceJava);
			j.setSuccess(true);
		}else{
			j.setSuccess(false);
		}
		return j;
	}
	 
	 /**
	 * 添加按钮JAVA增强
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(CgformEnhanceJavaEntity cgformEnhanceJavaEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		List<CgformEnhanceJavaEntity> list =  cgformEnhanceJavaService.checkCgformEnhanceJavaEntity(cgformEnhanceJavaEntity);
		if(list!=null&&list.size()>0){
			message = "按钮编�?已�?存在";
			j.setMsg(message);
			return j;
		}
		
		if(!cgformEnhanceJavaService.checkClassOrSpringBeanIsExist(cgformEnhanceJavaEntity)){
			message = "类实例化失败，请检查�?";
			j.setMsg(message);
			return j;
		}
		
		if (StringUtil.isNotEmpty(cgformEnhanceJavaEntity.getId())) {
			message = "更新�?功";
			CgformEnhanceJavaEntity t = cgformEnhanceJavaService.get(CgformEnhanceJavaEntity.class, cgformEnhanceJavaEntity.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(cgformEnhanceJavaEntity, t);
				cgformEnhanceJavaService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加�?功";
			cgformEnhanceJavaService.save(cgformEnhanceJavaEntity);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	 /**
	 * 按钮java增强列表页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(CgformEnhanceJavaEntity cgformEnhanceJavaEntity, HttpServletRequest req) {
		//根�?�buttonCode和formId�?始化数�?�
		cgformEnhanceJavaEntity.setButtonCode("add");
		if (StringUtil.isNotEmpty(cgformEnhanceJavaEntity.getButtonCode())&&StringUtil.isNotEmpty(cgformEnhanceJavaEntity.getFormId())) {
			CgformEnhanceJavaEntity cgformEnhanceJavaEntityVo = cgformEnhanceJavaService.getCgformEnhanceJavaEntityByCodeFormId(cgformEnhanceJavaEntity.getButtonCode(), cgformEnhanceJavaEntity.getFormId(), cgformEnhanceJavaEntity.getEvent());
			 if(cgformEnhanceJavaEntityVo!=null){
				 cgformEnhanceJavaEntity = cgformEnhanceJavaEntityVo;
			 }
		}

		List<CgformButtonEntity> list = cgformButtonService.getCgformButtonListByFormId(cgformEnhanceJavaEntity.getFormId());
		if(list==null){
			list = new ArrayList<CgformButtonEntity>();
		}
		req.setAttribute("buttonList", list);
		req.setAttribute("cgformEnhanceJavaPage", cgformEnhanceJavaEntity);
		return new ModelAndView("jeecg/cgform/enhance/cgformEnhanceJava");
	}
}
