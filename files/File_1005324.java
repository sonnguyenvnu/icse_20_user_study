package org.jeecgframework.web.system.sms.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSNotice;
import org.jeecgframework.web.system.pojo.base.TSNoticeReadUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.sms.entity.TSSmsEntity;
import org.jeecgframework.web.system.sms.service.TSSmsServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



/**   
 * @Title: Controller
 * @Description: æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨
 * @author onlineGenerator
 * @date 2014-09-18 00:01:53
 * @version V1.0   
 *
 */
//@Scope("prototype")
@Controller
@RequestMapping("/tSSmsController")
public class TSSmsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TSSmsController.class);

	@Autowired
	private TSSmsServiceI tSSmsService;
	@Autowired
	private SystemService systemService;

	/**
	 * æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨åˆ—è¡¨ é¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "tSSms")
	public ModelAndView tSSms(HttpServletRequest request) {
		return new ModelAndView("system/sms/tSSmsList");
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
	public void datagrid(TSSmsEntity tSSms,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSSmsEntity.class, dataGrid);
		//æŸ¥è¯¢æ?¡ä»¶ç»„è£…å™¨
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSms, request.getParameterMap());
		try{
		//è‡ªå®šä¹‰è¿½åŠ æŸ¥è¯¢æ?¡ä»¶
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tSSmsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * åˆ é™¤æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TSSmsEntity tSSms, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tSSms = systemService.getEntity(TSSmsEntity.class, tSSms.getId());
		message = "æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨åˆ é™¤æˆ?åŠŸ";
		try{
			tSSmsService.delete(tSSms);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨åˆ é™¤å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * æ‰¹é‡?åˆ é™¤æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨åˆ é™¤æˆ?åŠŸ";
		try{
			for(String id:ids.split(",")){
				TSSmsEntity tSSms = systemService.getEntity(TSSmsEntity.class, 
				id
				);
				tSSmsService.delete(tSSms);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨åˆ é™¤å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * æ·»åŠ æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TSSmsEntity tSSms, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨æ·»åŠ æˆ?åŠŸ";
		try{
			tSSmsService.save(tSSms);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨æ·»åŠ å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * æ›´æ–°æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TSSmsEntity tSSms, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨æ›´æ–°æˆ?åŠŸ";
		TSSmsEntity t = tSSmsService.get(TSSmsEntity.class, tSSms.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tSSms, t);
			tSSmsService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨æ›´æ–°å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨æ–°å¢žé¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TSSmsEntity tSSms, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSSms.getId())) {
			tSSms = tSSmsService.getEntity(TSSmsEntity.class, tSSms.getId());
			req.setAttribute("tSSmsPage", tSSms);
		}
		return new ModelAndView("system/sms/tSSms-add");
	}
	/**
	 * æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨ç¼–è¾‘é¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TSSmsEntity tSSms, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSSms.getId())) {
			tSSms = tSSmsService.getEntity(TSSmsEntity.class, tSSms.getId());
			req.setAttribute("tSSmsPage", tSSms);
		}
		return new ModelAndView("system/sms/tSSms-update");
	}
	
	/**
	 * å¯¼å…¥åŠŸèƒ½è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("system/sms/tSSmsUpload");
	}
	
	/**
	 * å¯¼å‡ºexcel
	 * 
	 * @param request
	 * @param response

	@RequestMapping(params = "exportXls")
	public void exportXls(TSSmsEntity tSSms,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨";
			// æ ¹æ?®æµ?è§ˆå™¨è¿›è¡Œè½¬ç ?ï¼Œä½¿å…¶æ”¯æŒ?ä¸­æ–‡æ–‡ä»¶å??
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// äº§ç”Ÿå·¥ä½œç°¿å¯¹è±¡
			HSSFWorkbook workbook = null;
			CriteriaQuery cq = new CriteriaQuery(TSSmsEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSms, request.getParameterMap());
			
			List<TSSmsEntity> tSSmss = this.tSSmsService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExportParams("æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨åˆ—è¡¨", "å¯¼å‡ºäºº:"+ResourceUtil.getSessionUser().getRealName(),
					"å¯¼å‡ºä¿¡æ?¯"), TSSmsEntity.class, tSSmss);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}	 */
	/**
	 * å¯¼å‡ºexcel ä½¿æ¨¡æ?¿
	 * 
	 * @param request
	 * @param response
	 
	@RequestMapping(params = "exportXlsByT")
	public void exportXlsByT(TSSmsEntity tSSms,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨";
			// æ ¹æ?®æµ?è§ˆå™¨è¿›è¡Œè½¬ç ?ï¼Œä½¿å…¶æ”¯æŒ?ä¸­æ–‡æ–‡ä»¶å??
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// äº§ç”Ÿå·¥ä½œç°¿å¯¹è±¡
			HSSFWorkbook workbook = null;
			workbook = ExcelExportUtil.exportExcel(new ExportParams("æ¶ˆæ?¯å?‘é€?è®°å½•è¡¨åˆ—è¡¨", "å¯¼å‡ºäºº:"+ResourceUtil.getSessionUser().getRealName(),
					"å¯¼å‡ºä¿¡æ?¯"), TSSmsEntity.class, null);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
	*/
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
//			params.setSecondTitleRows(1);
//			params.setNeedSave(true);
//			try {
//				List<TSSmsEntity> listTSSmsEntitys = 
//					(List<TSSmsEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),TSSmsEntity.class,params);
//				for (TSSmsEntity tSSms : listTSSmsEntitys) {
//					tSSmsService.save(tSSms);
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


	/**
	 * ä»Šå¤©éœ€è¦?æ??é†’çš„ã€?ç³»ç»Ÿä¿¡æ?¯ã€‘
	 * 
	 */
	@RequestMapping(params = "getMsgs")
	@ResponseBody
	public AjaxJson getMsgs(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		List<TSSmsEntity> list = new ArrayList<TSSmsEntity>();
		try {
			String curUser = ResourceUtil.getSessionUser().getUserName();
			String isSend = ResourceUtil.getConfigByName("sms.tip.control");
			if("1".equals(isSend)){
				DataGrid dataGrid = new DataGrid();
				dataGrid.setRows(20);//æŸ¥å‡ºæœ€æ–°20æ?¡è®°å½•
				CriteriaQuery cq = new CriteriaQuery(TSSmsEntity.class, dataGrid);
				cq.eq("esType", "3");
				cq.eq("esReceiver", curUser);
				cq.eq("isRead", 0);
				cq.addOrder("esSendtime", SortDirection.desc);
				cq.add();
				this.tSSmsService.getDataGridReturn(cq, true);
				list = dataGrid.getResults();
				int size = list.size();
				//3.èŽ·å?–å½“å‰?æ—¶é—´æ˜¯å?¦æœ‰æ??é†’çš„ç³»ç»Ÿæ¶ˆæ?¯
				if(size>0){
					for(TSSmsEntity tSSmsEntity:list){
						//æŸ¥è¯¢ä¹‹å?Žï¼Œå?Œæ—¶å°†è¯¥æ?¡ä¿¡æ?¯ç½®ä¸ºâ€?å·²æ??é†’â€œ
						if("1".equals(tSSmsEntity.getEsStatus())){
							tSSmsEntity.setEsStatus("2");
							this.tSSmsService.saveOrUpdate(tSSmsEntity);
						}
					}
					j.setSuccess(true);
					j.setMsg("æ‚¨æ”¶åˆ°ç³»ç»Ÿæ¶ˆæ?¯ï¼Œè¯·åˆ°ã€?æŽ§åˆ¶é?¢æ?¿ã€‘ä¸‹\"ç³»ç»Ÿæ¶ˆæ?¯\"è?œå?•æŸ¥çœ‹ï¼?");
				} else {
					j.setSuccess(true);
					j.setMsg("");
				}
		    }
		} catch (Exception e) {
			j.setSuccess(false);
			logger.info("èŽ·å?–å?‘é€?ä¿¡æ?¯å¤±è´¥");
		}
		return j;
	}

	/**
	 * å½“å‰?ç™»å½•äººå½“æ—¥ã€?ç³»ç»Ÿä¿¡æ?¯ã€‘è¯¦ç»†ä¿¡æ?¯
	 * 
	 */
	
	@RequestMapping(params = "getSysInfos")
	public ModelAndView getSysInfos(HttpServletRequest request) {
		
		//1. å?–å¾—ç³»ç»Ÿå½“å‰?ç™»å½•äººID
		String curUser = ResourceUtil.getSessionUser().getUserName();
		//2.æŸ¥è¯¢å½“å‰?ç™»å½•äººçš„æ¶ˆæ?¯ç±»åž‹ä¸º"3",å¹¶ä¸”åœ¨æŸ¥è¯¢çš„èŠ‚ç‚¹ä¹‹å?Žä¸€ä¸ªå°?æ—¶å†…çš„ä¿¡æ?¯
		//å½“å‰?æ—¶é—´
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String curDate = sdf.format(new Date());
		List<TSSmsEntity> list = this.tSSmsService.getMsgsList(curUser,curDate);
		request.setAttribute("smsList", list);
		
		return new ModelAndView("system/sms/tSSmsDetailList");
	}

	
	/**
	 * é€šçŸ¥åˆ—è¡¨ï¼ˆé˜…è¯»ï¼‰
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goMySmsList")
	public ModelAndView goMySmsList(HttpServletRequest request) {
		return new ModelAndView("system/sms/mySmsList");
	}
	/**
	 * é€šçŸ¥è¯¦æƒ…
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goSmsDetail")
	public ModelAndView goSmsDetail(TSSmsEntity tSSms,HttpServletRequest request) {
		if (StringUtil.isNotEmpty(tSSms.getId())) {
			tSSms = this.systemService.getEntity(TSSmsEntity.class, tSSms.getId());
			request.setAttribute("tSSms", tSSms);
			if(tSSms.getIsRead() == 0){
				tSSms.setIsRead(1);
				systemService.saveOrUpdate(tSSms);
			}
		}
		return new ModelAndView("system/sms/mySms-info");
	}
	
	/**
	 * é˜…è¯»é€šçŸ¥
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "updateSmsRead")
	@ResponseBody
	public AjaxJson updateSmsRead(TSSmsEntity tSSms,HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if (StringUtil.isNotEmpty(tSSms.getId())) {
				tSSms = this.systemService.getEntity(TSSmsEntity.class, tSSms.getId());
				if(tSSms.getIsRead() == 0){
					tSSms.setIsRead(1);
					systemService.saveOrUpdate(tSSms);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * easyui AJAXè¯·æ±‚æ•°æ?®
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "mydatagrid")
	public void mydatagrid(TSSmsEntity tSSms,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSSmsEntity.class, dataGrid);
		//æŸ¥è¯¢æ?¡ä»¶ç»„è£…å™¨
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSSms, request.getParameterMap());
		String curUser = ResourceUtil.getSessionUser().getUserName();
		try{
		//è‡ªå®šä¹‰è¿½åŠ æŸ¥è¯¢æ?¡ä»¶
			cq.eq("esType", "3");
			cq.eq("esReceiver", curUser);
//			cq.eq("isRead", 0);
//			cq.addOrder("isRead", SortDirection.asc);
			cq.addOrder("esSendtime", SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tSSmsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * å?–å¾—å?¯è¯»çš„æ¶ˆæ?¯
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getMessageList")
	@ResponseBody
	public AjaxJson getMessageList(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			j.setObj(0);
			List<TSSmsEntity> list = new ArrayList<TSSmsEntity>();
			//1. å?–å¾—ç³»ç»Ÿå½“å‰?ç™»å½•äººID
			String curUser = ResourceUtil.getSessionUser().getUserName();
			//2.æŸ¥è¯¢å½“å‰?ç™»å½•äººçš„æ¶ˆæ?¯ç±»åž‹ä¸º"3"
//			//å½“å‰?æ—¶é—´
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String curDate = sdf.format(new Date());		
		
			String isSend = ResourceUtil.getConfigByName("sms.tip.control");
			if("1".equals(isSend)){
				DataGrid dataGrid = new DataGrid();
				dataGrid.setRows(20);//æŸ¥å‡ºæœ€æ–°20æ?¡è®°å½•
				CriteriaQuery cq = new CriteriaQuery(TSSmsEntity.class, dataGrid);
				cq.eq("esType", "3");
				cq.eq("esReceiver", curUser);
				cq.eq("isRead", 0);
				cq.addOrder("esSendtime", SortDirection.desc);
				cq.add();
				this.tSSmsService.getDataGridReturn(cq, true);
//				list = this.tSSmsService.getMsgsList(curUser,curDate);
				list = dataGrid.getResults();
				//å°†Listè½¬æ?¢æˆ?JSONå­˜å‚¨
				JSONArray result = new JSONArray();
		        if(list!=null && list.size()>0){
		        	for(int i=0;i<list.size();i++){
		    			JSONObject jsonParts = new JSONObject();
		    			jsonParts.put("id", list.get(i).getId());
		    			jsonParts.put("esTitle", list.get(i).getEsTitle());
		    			jsonParts.put("esSender", list.get(i).getEsSender());
		    			jsonParts.put("esContent", list.get(i).getEsContent());
		    			jsonParts.put("esSendtime", list.get(i).getEsSendtime());
		    			jsonParts.put("esStatus", list.get(i).getEsStatus());
		    			if(list.get(i).getEsSendtime()!=null){
		    				SimpleDateFormat sdformat = new SimpleDateFormat("h:mm a");
		    				jsonParts.put("esSendtimeTxt", sdformat.format(list.get(i).getEsSendtime()));
		    			}
		    			result.add(jsonParts);	
		    		}
		        	j.setObj(list.size());
				}
				
				Map<String,Object> attrs = new HashMap<String, Object>();
				attrs.put("messageList", result);
				String tip = MutiLangUtil.getLang("message.tip");
				attrs.put("tip", tip);
				String seeAll = MutiLangUtil.getLang("message.seeAll");
				attrs.put("seeAll", seeAll);
				j.setAttributes(attrs);
		    }
		} catch (Exception e) {
			j.setSuccess(false);
		}
		return j;
	}
	
	/**
	 * é˜…è¯»æ¶ˆæ?¯(æ¶ˆæ?¯çŠ¶æ€?ç½®æˆ?å·²æ??é†’)
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "readMessage")
	@ResponseBody
	public AjaxJson readMessage(String messageId,HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtil.isNotEmpty(messageId)){
				TSSmsEntity tSSmsEntity = this.systemService.get(TSSmsEntity.class, messageId);
				if(tSSmsEntity!=null){
					tSSmsEntity.setEsStatus("2");
					this.tSSmsService.saveOrUpdate(tSSmsEntity);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * èŽ·å?–æ¶ˆæ?¯
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "getMsg")
	@ResponseBody
	public AjaxJson getMsg(String msgId,HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			if(StringUtil.isNotEmpty(msgId)){
				TSSmsEntity tSSmsEntity = this.systemService.get(TSSmsEntity.class, msgId);
				Map<String,Object> attrs = new HashMap<String, Object>();
				attrs.put("msginfo", tSSmsEntity);
				j.setAttributes(attrs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
}
