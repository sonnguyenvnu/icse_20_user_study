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

import org.jeecgframework.web.system.sms.entity.TSSmsTemplateSqlEntity;
import org.jeecgframework.web.system.sms.service.TSSmsTemplateSqlServiceI;
import org.jeecgframework.web.system.sms.util.TuiSongMsgUtil;



/**   
 * @Title: Controller
 * @Description: æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨
 * @author onlineGenerator
 * @date 2014-09-17 23:44:17
 * @version V1.0   
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/tSSmsTemplateSqlController")
public class TSSmsTemplateSqlController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TSSmsTemplateSqlController.class);

	@Autowired
	private TSSmsTemplateSqlServiceI tSSmsTemplateSqlService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * æ¶ˆæ?¯æŽ¨é€?æµ‹è¯•
	 * @param tSSmsTemplateSql
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "pushMsg")
	@ResponseBody
	public AjaxJson pushMsg(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtils.isBlank(tSSmsTemplateSql.getCode())){
			j.setSuccess(false);
			j.setMsg("é…?ç½®CODEä¸?èƒ½ä¸ºç©º");
		}else {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", "4028d881436d514601436d521ae80165");
			String r = TuiSongMsgUtil.sendMessage("æ¶ˆæ?¯æŽ¨é€?æµ‹è¯•333","2", tSSmsTemplateSql.getCode(), map, "411944058@qq.com");
			if (!"success".equals(r)){
				j.setSuccess(false);
				j.setMsg(r);
			}
		}
		return j;
	}

	/**
	 * æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨åˆ—è¡¨ é¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "tSSmsTemplateSql")
	public ModelAndView tSSmsTemplateSql(HttpServletRequest request) {
		return new ModelAndView("system/sms/tSSmsTemplateSqlList");
	}

	/**
	 * easyui AJAXè¯·æ±‚æ•°æ?®
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TSSmsTemplateSqlEntity tSSmsTemplateSql,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSSmsTemplateSqlEntity.class, dataGrid);
		//æŸ¥è¯¢æ?¡ä»¶ç»„è£…å™¨
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSmsTemplateSql, request.getParameterMap());
		try{
		//è‡ªå®šä¹‰è¿½åŠ æŸ¥è¯¢æ?¡ä»¶
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tSSmsTemplateSqlService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * åˆ é™¤æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tSSmsTemplateSql = systemService.getEntity(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
		message = "æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨åˆ é™¤æˆ?åŠŸ";
		try{
			tSSmsTemplateSqlService.delete(tSSmsTemplateSql);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨åˆ é™¤å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * æ‰¹é‡?åˆ é™¤æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨åˆ é™¤æˆ?åŠŸ";
		try{
			for(String id:ids.split(",")){
				TSSmsTemplateSqlEntity tSSmsTemplateSql = systemService.getEntity(TSSmsTemplateSqlEntity.class, 
				id
				);
				tSSmsTemplateSqlService.delete(tSSmsTemplateSql);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨åˆ é™¤å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * æ·»åŠ æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨æ·»åŠ æˆ?åŠŸ";
		try{
			tSSmsTemplateSqlService.save(tSSmsTemplateSql);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨æ·»åŠ å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * æ›´æ–°æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨æ›´æ–°æˆ?åŠŸ";
		TSSmsTemplateSqlEntity t = tSSmsTemplateSqlService.get(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tSSmsTemplateSql, t);
			tSSmsTemplateSqlService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨æ›´æ–°å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨æ–°å¢žé¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSSmsTemplateSql.getId())) {
			tSSmsTemplateSql = tSSmsTemplateSqlService.getEntity(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
			req.setAttribute("tSSmsTemplateSqlPage", tSSmsTemplateSql);
		}
		return new ModelAndView("system/sms/tSSmsTemplateSql-add");
	}
	/**
	 * æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨ç¼–è¾‘é¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TSSmsTemplateSqlEntity tSSmsTemplateSql, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSSmsTemplateSql.getId())) {
			tSSmsTemplateSql = tSSmsTemplateSqlService.getEntity(TSSmsTemplateSqlEntity.class, tSSmsTemplateSql.getId());
			req.setAttribute("tSSmsTemplateSqlPage", tSSmsTemplateSql);
		}
		return new ModelAndView("system/sms/tSSmsTemplateSql-update");
	}
	
	/**
	 * å¯¼å…¥åŠŸèƒ½è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("system/sms/tSSmsTemplateSqlUpload");
	}
	
	/**
	 * å¯¼å‡ºexcel
	 * 
	 * @param request
	 * @param response
	 */
//	@RequestMapping(params = "exportXls")
//	public void exportXls(TSSmsTemplateSqlEntity tSSmsTemplateSql,HttpServletRequest request,HttpServletResponse response
//			, DataGrid dataGrid) {
//		response.setContentType("application/vnd.ms-excel");
//		String codedFileName = null;
//		OutputStream fOut = null;
//		try {
//			codedFileName = "æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨";
//			// æ ¹æ?®æµ?è§ˆå™¨è¿›è¡Œè½¬ç ?ï¼Œä½¿å…¶æ”¯æŒ?ä¸­æ–‡æ–‡ä»¶å??
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
//			// äº§ç”Ÿå·¥ä½œç°¿å¯¹è±¡
//			HSSFWorkbook workbook = null;
//			CriteriaQuery cq = new CriteriaQuery(TSSmsTemplateSqlEntity.class, dataGrid);
//			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSmsTemplateSql, request.getParameterMap());
//			
//			List<TSSmsTemplateSqlEntity> tSSmsTemplateSqls = this.tSSmsTemplateSqlService.getListByCriteriaQuery(cq,false);
//			workbook = ExcelExportUtil.exportExcel(new ExportParams("æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨åˆ—è¡¨", "å¯¼å‡ºäºº:"+ResourceUtil.getSessionUser().getRealName(),
//					"å¯¼å‡ºä¿¡æ?¯"), TSSmsTemplateSqlEntity.class, tSSmsTemplateSqls);
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
//	/**
//	 * å¯¼å‡ºexcel ä½¿æ¨¡æ?¿
//	 * 
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(params = "exportXlsByT")
//	public void exportXlsByT(TSSmsTemplateSqlEntity tSSmsTemplateSql,HttpServletRequest request,HttpServletResponse response
//			, DataGrid dataGrid) {
//		response.setContentType("application/vnd.ms-excel");
//		String codedFileName = null;
//		OutputStream fOut = null;
//		try {
//			codedFileName = "æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨";
//			// æ ¹æ?®æµ?è§ˆå™¨è¿›è¡Œè½¬ç ?ï¼Œä½¿å…¶æ”¯æŒ?ä¸­æ–‡æ–‡ä»¶å??
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
//			// äº§ç”Ÿå·¥ä½œç°¿å¯¹è±¡
//			HSSFWorkbook workbook = null;
//			workbook = ExcelExportUtil.exportExcel(new ExportParams("æ¶ˆæ?¯æ¨¡æ?¿_ä¸šåŠ¡SQLé…?ç½®è¡¨åˆ—è¡¨", "å¯¼å‡ºäºº:"+ResourceUtil.getSessionUser().getRealName(),
//					"å¯¼å‡ºä¿¡æ?¯"), TSSmsTemplateSqlEntity.class, null);
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
//			MultipartFile file = entity.getValue();// èŽ·å?–ä¸Šä¼ æ–‡ä»¶å¯¹è±¡
//			ImportParams params = new ImportParams();
//			params.setTitleRows(2);
//			//params.setSecondTitleRows(1);
//			params.setNeedSave(true);
//			try {
//				List<TSSmsTemplateSqlEntity> listTSSmsTemplateSqlEntitys = 
//					(List<TSSmsTemplateSqlEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),TSSmsTemplateSqlEntity.class,params);
//				for (TSSmsTemplateSqlEntity tSSmsTemplateSql : listTSSmsTemplateSqlEntitys) {
//					tSSmsTemplateSqlService.save(tSSmsTemplateSql);
//				}
//				j.setMsg("æ–‡ä»¶å¯¼å…¥æˆ?åŠŸï¼?");
//			} catch (Exception e) {
//				j.setMsg("æ–‡ä»¶å¯¼å…¥å¤±è´¥ï¼?");
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
