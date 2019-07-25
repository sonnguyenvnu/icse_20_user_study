package org.jeecgframework.web.system.sms.controller;
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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.web.system.sms.entity.TSSmsSqlEntity;
import org.jeecgframework.web.system.sms.service.TSSmsSqlServiceI;



/**   
 * @Title: Controller
 * @Description: 业务SQL表
 * @author onlineGenerator
 * @date 2014-09-17 23:52:58
 * @version V1.0   
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/tSSmsSqlController")
public class TSSmsSqlController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TSSmsSqlController.class);

	@Autowired
	private TSSmsSqlServiceI tSSmsSqlService;
	@Autowired
	private SystemService systemService;


	/**
	 * 业务SQL表列表 页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tSSmsSql")
	public ModelAndView tSSmsSql(HttpServletRequest request) {
		return new ModelAndView("system/sms/tSSmsSqlList");
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
	public void datagrid(TSSmsSqlEntity tSSmsSql,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSSmsSqlEntity.class, dataGrid);
		//查询�?�件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSmsSql, request.getParameterMap());
		try{
		//自定义追加查询�?�件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除业务SQL表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TSSmsSqlEntity tSSmsSql, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
		message = "业务SQL表删除�?功";
		try{
			systemService.delete(tSSmsSql);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "业务SQL表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批�?删除业务SQL表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "业务SQL表删除�?功";
		try{
			for(String id:ids.split(",")){
				TSSmsSqlEntity tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, 
				id
				);
				systemService.delete(tSSmsSql);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "业务SQL表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加业务SQL表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TSSmsSqlEntity tSSmsSql, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "业务SQL表添加�?功";
		try{
			tSSmsSqlService.save(tSSmsSql);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "业务SQL表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新业务SQL表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TSSmsSqlEntity tSSmsSql, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "业务SQL表更新�?功";
		TSSmsSqlEntity t = systemService.get(TSSmsSqlEntity.class, tSSmsSql.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tSSmsSql, t);
			systemService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "业务SQL表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 业务SQL表新增页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TSSmsSqlEntity tSSmsSql, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSSmsSql.getId())) {
			tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
			req.setAttribute("tSSmsSqlPage", tSSmsSql);
		}
		return new ModelAndView("system/sms/tSSmsSql-add");
	}
	/**
	 * 业务SQL表编辑页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TSSmsSqlEntity tSSmsSql, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSSmsSql.getId())) {
			tSSmsSql = systemService.getEntity(TSSmsSqlEntity.class, tSSmsSql.getId());
			req.setAttribute("tSSmsSqlPage", tSSmsSql);
		}
		return new ModelAndView("system/sms/tSSmsSql-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("system/sms/tSSmsSqlUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
//	@RequestMapping(params = "exportXls")
//	public void exportXls(TSSmsSqlEntity tSSmsSql,HttpServletRequest request,HttpServletResponse response
//			, DataGrid dataGrid) {
//		response.setContentType("application/vnd.ms-excel");
//		String codedFileName = null;
//		OutputStream fOut = null;
//		try {
//			codedFileName = "业务SQL表";
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
//			CriteriaQuery cq = new CriteriaQuery(TSSmsSqlEntity.class, dataGrid);
//			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSmsSql, request.getParameterMap());
//			
//			List<TSSmsSqlEntity> tSSmsSqls = this.systemService.getListByCriteriaQuery(cq,false);
//			workbook = ExcelExportUtil.exportExcel(new ExportParams("业务SQL表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
//					"导出信�?�"), TSSmsSqlEntity.class, tSSmsSqls);
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
//	public void exportXlsByT(TSSmsSqlEntity tSSmsSql,HttpServletRequest request,HttpServletResponse response
//			, DataGrid dataGrid) {
//		response.setContentType("application/vnd.ms-excel");
//		String codedFileName = null;
//		OutputStream fOut = null;
//		try {
//			codedFileName = "业务SQL表";
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
//			workbook = ExcelExportUtil.exportExcel(new ExportParams("业务SQL表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
//					"导出信�?�"), TSSmsSqlEntity.class, null);
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
//				List<TSSmsSqlEntity> listTSSmsSqlEntitys = 
//					(List<TSSmsSqlEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),TSSmsSqlEntity.class,params);
//				for (TSSmsSqlEntity tSSmsSql : listTSSmsSqlEntitys) {
//					systemService.save(tSSmsSql);
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
}
