package org.jeecgframework.web.cgform.controller.fillrule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.entity.fillrule.TSFillRuleEntity;
import org.jeecgframework.web.cgform.service.fillrule.TSFillRuleServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONArray;

/**   
 * @Title: Controller  
 * @Description: 填值规则表
 * @author onlineGenerator
 * @date 2018-01-04 19:01:44
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tSFillRuleController")
public class TSFillRuleController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(TSFillRuleController.class);

	@Autowired
	private TSFillRuleServiceI tSFillRuleService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 填值规则表列表 页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("jeecg/cgform/fillrule/tSFillRuleList");
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
	public void datagrid(TSFillRuleEntity tSFillRule,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSFillRuleEntity.class, dataGrid);
		//查询�?�件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSFillRule, request.getParameterMap());
		try{
		//自定义追加查询�?�件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tSFillRuleService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除填值规则表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TSFillRuleEntity tSFillRule, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tSFillRule = systemService.getEntity(TSFillRuleEntity.class, tSFillRule.getId());
		message = "填值规则表删除�?功";
		try{
			tSFillRuleService.delete(tSFillRule);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "填值规则表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批�?删除填值规则表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "填值规则表删除�?功";
		try{
			for(String id:ids.split(",")){
				TSFillRuleEntity tSFillRule = systemService.getEntity(TSFillRuleEntity.class, 
				id
				);
				tSFillRuleService.delete(tSFillRule);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "填值规则表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加填值规则表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TSFillRuleEntity tSFillRule, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "填值规则表添加�?功";
		try{
			tSFillRuleService.save(tSFillRule);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "填值规则表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新填值规则表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TSFillRuleEntity tSFillRule, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "填值规则表更新�?功";
		TSFillRuleEntity t = tSFillRuleService.get(TSFillRuleEntity.class, tSFillRule.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tSFillRule, t);
			tSFillRuleService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "填值规则表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 填值规则表新增页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TSFillRuleEntity tSFillRule, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSFillRule.getId())) {
			tSFillRule = tSFillRuleService.getEntity(TSFillRuleEntity.class, tSFillRule.getId());
			req.setAttribute("tSFillRulePage", tSFillRule);
		}
		return new ModelAndView("jeecg/cgform/fillrule/tSFillRule-add");
	}
	/**
	 * 填值规则表编辑页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TSFillRuleEntity tSFillRule, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSFillRule.getId())) {
			tSFillRule = tSFillRuleService.getEntity(TSFillRuleEntity.class, tSFillRule.getId());
			req.setAttribute("tSFillRulePage", tSFillRule);
		}
		return new ModelAndView("jeecg/cgform/fillrule/tSFillRule-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","tSFillRuleController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TSFillRuleEntity tSFillRule,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TSFillRuleEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSFillRule, request.getParameterMap());
		List<TSFillRuleEntity> tSFillRules = this.tSFillRuleService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"填值规则表");
		modelMap.put(NormalExcelConstants.CLASS,TSFillRuleEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("填值规则表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信�?�"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tSFillRules);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模�?�
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TSFillRuleEntity tSFillRule,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"填值规则表");
    	modelMap.put(NormalExcelConstants.CLASS,TSFillRuleEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("填值规则表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信�?�"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获�?�上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<TSFillRuleEntity> listTSFillRuleEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TSFillRuleEntity.class,params);
				for (TSFillRuleEntity tSFillRule : listTSFillRuleEntitys) {
					tSFillRuleService.save(tSFillRule);
				}
				j.setMsg("文件导入�?功�?");
			} catch (Exception e) {
				j.setMsg("文件导入失败�?");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage<List<TSFillRuleEntity>> list() {
		List<TSFillRuleEntity> listTSFillRules=tSFillRuleService.getList(TSFillRuleEntity.class);
		return Result.success(listTSFillRules);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage<?> get(@PathVariable("id") String id) {
		TSFillRuleEntity task = tSFillRuleService.get(TSFillRuleEntity.class, id);
		if (task == null) {
			return Result.error("根�?�ID获�?�填值规则表信�?�为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage<?> create(@RequestBody TSFillRuleEntity tSFillRule, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回�?�400错误�?�?�json格�?的错误信�?�.
		Set<ConstraintViolation<TSFillRuleEntity>> failures = validator.validate(tSFillRule);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//�?存
		try{
			tSFillRuleService.save(tSFillRule);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("填值规则表信�?��?存失败");
		}
		return Result.success(tSFillRule);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage<?> update(@RequestBody TSFillRuleEntity tSFillRule) {
		//调用JSR303 Bean Validator进行校验，如果出错返回�?�400错误�?�?�json格�?的错误信�?�.
		Set<ConstraintViolation<TSFillRuleEntity>> failures = validator.validate(tSFillRule);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//�?存
		try{
			tSFillRuleService.saveOrUpdate(tSFillRule);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新填值规则表信�?�失败");
		}

		//按Restful约定，返回204状�?�?, 无内容. 也�?�以返回200状�?�?.
		return Result.success("更新填值规则表信�?��?功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseMessage<?> delete(@PathVariable("id") String id) {
		logger.info("delete[{}]" , id);
		// 验�?
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID�?能为空");
		}
		try {
			tSFillRuleService.deleteEntityById(TSFillRuleEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("填值规则表删除失败");
		}

		return Result.success();
	}
}
