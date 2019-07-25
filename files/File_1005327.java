package org.jeecgframework.web.system.sms.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.web.system.sms.entity.TSSmsTemplateEntity;
import org.jeecgframework.web.system.sms.entity.TSSmsTemplateSqlEntity;
import org.jeecgframework.web.system.sms.service.TSSmsTemplateServiceI;
import org.jeecgframework.web.system.sms.util.TuiSongMsgUtil;



/**   
 * @Title: Controller
 * @Description: 消�?�模本表
 * @author onlineGenerator
 * @date 2014-09-17 23:52:46
 * @version V1.0   
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/tSSmsTemplateController")
public class TSSmsTemplateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TSSmsTemplateController.class);

	@Autowired
	private TSSmsTemplateServiceI tSSmsTemplateService;
	@Autowired
	private SystemService systemService;


	/**
	 * 消�?�模本表列表 页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tSSmsTemplate")
	public ModelAndView tSSmsTemplate(HttpServletRequest request) {
		return new ModelAndView("system/sms/tSSmsTemplateList");
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
	public void datagrid(TSSmsTemplateEntity tSSmsTemplate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSSmsTemplateEntity.class, dataGrid);
		//查询�?�件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSmsTemplate, request.getParameterMap());
		try{
		//自定义追加查询�?�件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tSSmsTemplateService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除消�?�模本表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TSSmsTemplateEntity tSSmsTemplate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tSSmsTemplate = systemService.getEntity(TSSmsTemplateEntity.class, tSSmsTemplate.getId());
		message = "消�?�模本表删除�?功";
		try{
			tSSmsTemplateService.delete(tSSmsTemplate);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "消�?�模本表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批�?删除消�?�模本表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "消�?�模本表删除�?功";
		try{
			for(String id:ids.split(",")){
				TSSmsTemplateEntity tSSmsTemplate = systemService.getEntity(TSSmsTemplateEntity.class, 
				id
				);
				tSSmsTemplateService.delete(tSSmsTemplate);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "消�?�模本表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加消�?�模本表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TSSmsTemplateEntity tSSmsTemplate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "消�?�模本表添加�?功";
		try{
			tSSmsTemplateService.save(tSSmsTemplate);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "消�?�模本表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新消�?�模本表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TSSmsTemplateEntity tSSmsTemplate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "消�?�模本表更新�?功";
		TSSmsTemplateEntity t = tSSmsTemplateService.get(TSSmsTemplateEntity.class, tSSmsTemplate.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tSSmsTemplate, t);
			tSSmsTemplateService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "消�?�模本表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 消�?�模本表新增页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TSSmsTemplateEntity tSSmsTemplate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSSmsTemplate.getId())) {
			tSSmsTemplate = tSSmsTemplateService.getEntity(TSSmsTemplateEntity.class, tSSmsTemplate.getId());
			req.setAttribute("tSSmsTemplatePage", tSSmsTemplate);
		}
		return new ModelAndView("system/sms/tSSmsTemplate-add");
	}
	/**
	 * 消�?�模本表编辑页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TSSmsTemplateEntity tSSmsTemplate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSSmsTemplate.getId())) {
			tSSmsTemplate = tSSmsTemplateService.getEntity(TSSmsTemplateEntity.class, tSSmsTemplate.getId());
			req.setAttribute("tSSmsTemplatePage", tSSmsTemplate);
		}
		return new ModelAndView("system/sms/tSSmsTemplate-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("system/sms/tSSmsTemplateUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
//	@RequestMapping(params = "exportXls")
//	public void exportXls(TSSmsTemplateEntity tSSmsTemplate,HttpServletRequest request,HttpServletResponse response
//			, DataGrid dataGrid) {
//		response.setContentType("application/vnd.ms-excel");
//		String codedFileName = null;
//		OutputStream fOut = null;
//		try {
//			codedFileName = "消�?�模本表";
//			// 根�?��?览器进行转�?，使其支�?中文文件�??
//			if (BrowserUtils.isIE(request)) {
//				response.setHeader(
//						"content-disposition",
//						"attachment;filename="
//								+ java.net.URLEncoder.encode(codedFileName,
//										"UTF-8") + ".xls");
//			} else {
//				String newtitle = new String(codedFileName.getBytes("UTF-8"),
//						"ISO8859-1");
//				response.setHeader("content-disposition",
//						"attachment;filename=" + newtitle + ".xls");
//			}
//			// 产生工作簿对象
//			HSSFWorkbook workbook = null;
//			CriteriaQuery cq = new CriteriaQuery(TSSmsTemplateEntity.class, dataGrid);
//			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSmsTemplate, request.getParameterMap());
//			
//			List<TSSmsTemplateEntity> tSSmsTemplates = this.tSSmsTemplateService.getListByCriteriaQuery(cq,false);
//			workbook = ExcelExportUtil.exportExcel(new ExportParams("消�?�模本表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
//					"导出信�?�"), TSSmsTemplateEntity.class, tSSmsTemplates);
//			fOut = response.getOutputStream();
//			workbook.write(fOut);
//		} catch (Exception e) {
//		} finally {
//			try {
//				fOut.flush();
//				fOut.close();
//			} catch (IOException e) {
//
//			}
//		}
//	}
	/**
	 * 导出excel 使模�?�
	 * 
	 * @param request
	 * @param response
	 */
//	@RequestMapping(params = "exportXlsByT")
//	public void exportXlsByT(TSSmsTemplateEntity tSSmsTemplate,HttpServletRequest request,HttpServletResponse response
//			, DataGrid dataGrid) {
//		response.setContentType("application/vnd.ms-excel");
//		String codedFileName = null;
//		OutputStream fOut = null;
//		try {
//			codedFileName = "消�?�模本表";
//			// 根�?��?览器进行转�?，使其支�?中文文件�??
//			if (BrowserUtils.isIE(request)) {
//				response.setHeader(
//						"content-disposition",
//						"attachment;filename="
//								+ java.net.URLEncoder.encode(codedFileName,
//										"UTF-8") + ".xls");
//			} else {
//				String newtitle = new String(codedFileName.getBytes("UTF-8"),
//						"ISO8859-1");
//				response.setHeader("content-disposition",
//						"attachment;filename=" + newtitle + ".xls");
//			}
//			// 产生工作簿对象
//			HSSFWorkbook workbook = null;
//			workbook = ExcelExportUtil.exportExcel(new ExportParams("消�?�模本表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
//					"导出信�?�"), TSSmsTemplateEntity.class, null);
//			fOut = response.getOutputStream();
//			workbook.write(fOut);
//		} catch (Exception e) {
//		} finally {
//			try {
//				fOut.flush();
//				fOut.close();
//			} catch (IOException e) {
//
//			}
//		}
//	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//			MultipartFile file = entity.getValue();// 获�?�上传文件对象
//			ImportParams params = new ImportParams();
//			params.setTitleRows(2);
//			params.setSecondTitleRows(1);
//			params.setNeedSave(true);
//			try {
//				List<TSSmsTemplateEntity> listTSSmsTemplateEntitys = 
//					(List<TSSmsTemplateEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),TSSmsTemplateEntity.class,params);
//				for (TSSmsTemplateEntity tSSmsTemplate : listTSSmsTemplateEntitys) {
//					tSSmsTemplateService.save(tSSmsTemplate);
//				}
//				j.setMsg("文件导入�?功�?");
//			} catch (Exception e) {
//				j.setMsg("文件导入失败�?");
//				logger.error(ExceptionUtil.getExceptionMessage(e));
//			}finally{
//				try {
//					file.getInputStream().close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		return j;
	}
	
	/**
	 * 模�?�消�?�推�?测试
	 * @param tSSmsTemplateSql
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "pushTestMsg")
	@ResponseBody
	public AjaxJson pushTestMsg(TSSmsTemplateEntity tSSmsTemplate, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			if (StringUtils.isBlank(tSSmsTemplate.getTemplateCode())){
				j.setSuccess(false);
				j.setMsg("模�?�CODE�?能为空");
			}else {
				tSSmsTemplate = tSSmsTemplateService.findUniqueByProperty(TSSmsTemplateEntity.class, "templateCode", tSSmsTemplate.getTemplateCode());
				Map<String,Object> data = new HashMap<String,Object>();
				String json = tSSmsTemplate.getTemplateTestJson();
				if(StringUtils.isEmpty(json)){
					j.setSuccess(false);
					j.setMsg("模�?�测试json�?能为空");
					return j;
				}
				data = JSONHelper.json2Map(json);
				String r = TuiSongMsgUtil.sendMessage(tSSmsTemplate.getTemplateCode(), data, "系统", "admin");
				if (!"success".equals(r)){
					j.setSuccess(false);
					j.setMsg(r);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("模�?�测试json异常");
		}
		return j;
	}
}
