package com.jeecg.demo.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ReflectHelper;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.alibaba.fastjson.JSONObject;
import com.jeecg.demo.entity.JformOrderCustomerEntity;
import com.jeecg.demo.entity.JformOrderMainEntity;
import com.jeecg.demo.entity.JformOrderTicketEntity;
import com.jeecg.demo.page.JformOrderCustomerPage;
import com.jeecg.demo.page.JformOrderMainPage;
import com.jeecg.demo.service.JformOrderMainServiceI;
/**   
 * @Title: Controller
 * @Description: è®¢å?•ä¸»ä¿¡æ?¯
 * @author onlineGenerator
 * @date 2017-09-17 11:49:08
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/jformOrderMainController")
@Api(value="orderMainRest",description="ä¸€å¯¹å¤šè®¢å?•ç®¡ç?†",tags="JformOrderMainController")
public class JformOrderMainController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JformOrderMainController.class);

	@Autowired
	private JformOrderMainServiceI jformOrderMainService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;

	/**
	 * è®¢å?•ä¸»ä¿¡æ?¯åˆ—è¡¨ é¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/orderOne2Many/jformOrderMainList");
	}
	
	/**
	 * è®¢å?•ä¸»ä¿¡æ?¯åˆ—è¡¨ é¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "mainlist")
	public ModelAndView mainlist(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/orderOne2Many/jformOrderMainListBase");
	}
	
	/**
	 * è®¢å?•ä¸»ä¿¡æ?¯åˆ—è¡¨ é¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "customerlist")
	public ModelAndView customerlist(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/orderOne2Many/jformOrderCustomerListBase");
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
	public void datagrid(JformOrderMainEntity jformOrderMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JformOrderMainEntity.class, dataGrid);
		//æŸ¥è¯¢æ?¡ä»¶ç»„è£…å™¨
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jformOrderMain);
		try{
		//è‡ªå®šä¹‰è¿½åŠ æŸ¥è¯¢æ?¡ä»¶
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.jformOrderMainService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * easyui AJAXè¯·æ±‚æ•°æ?®
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	
	@RequestMapping(params = "customerDatagrid")
	public void customerDatagrid(JformOrderCustomerEntity jformCustomer,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JformOrderCustomerEntity.class, dataGrid);
		if(jformCustomer.getFkId() == null || "".equals(jformCustomer.getFkId())){
		}else{
			//æŸ¥è¯¢æ?¡ä»¶ç»„è£…å™¨
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jformCustomer);
			cq.add();
			this.jformOrderMainService.getDataGridReturn(cq, true);
		}
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * åˆ é™¤è®¢å?•ä¸»ä¿¡æ?¯
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(JformOrderMainEntity jformOrderMain, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jformOrderMain = systemService.getEntity(JformOrderMainEntity.class, jformOrderMain.getId());
		String message = "è®¢å?•ä¸»ä¿¡æ?¯åˆ é™¤æˆ?åŠŸ";
		try{
			jformOrderMainService.delMain(jformOrderMain);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "è®¢å?•ä¸»ä¿¡æ?¯åˆ é™¤å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * æ‰¹é‡?åˆ é™¤è®¢å?•ä¸»ä¿¡æ?¯
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "è®¢å?•ä¸»ä¿¡æ?¯åˆ é™¤æˆ?åŠŸ";
		try{
			for(String id:ids.split(",")){
				JformOrderMainEntity jformOrderMain = systemService.getEntity(JformOrderMainEntity.class,
				id
				);
				jformOrderMainService.delMain(jformOrderMain);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "è®¢å?•ä¸»ä¿¡æ?¯åˆ é™¤å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * æ·»åŠ è®¢å?•ä¸»ä¿¡æ?¯
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(JformOrderMainEntity jformOrderMain,JformOrderMainPage jformOrderMainPage, HttpServletRequest request) {
		List<JformOrderCustomerEntity> jformOrderCustomerList =  jformOrderMainPage.getJformOrderCustomerList();
		List<JformOrderTicketEntity> jformOrderTicketList =  jformOrderMainPage.getJformOrderTicketList();
		AjaxJson j = new AjaxJson();
		String message = "æ·»åŠ æˆ?åŠŸ";
		try{
			jformOrderMainService.addMain(jformOrderMain, jformOrderCustomerList,jformOrderTicketList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "è®¢å?•ä¸»ä¿¡æ?¯æ·»åŠ å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(jformOrderMain);
		return j;
	}
	/**
	 * æ›´æ–°è®¢å?•ä¸»ä¿¡æ?¯
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(JformOrderMainEntity jformOrderMain,JformOrderMainPage jformOrderMainPage, HttpServletRequest request) {
		List<JformOrderCustomerEntity> jformOrderCustomerList =  jformOrderMainPage.getJformOrderCustomerList();
		List<JformOrderTicketEntity> jformOrderTicketList =  jformOrderMainPage.getJformOrderTicketList();
		AjaxJson j = new AjaxJson();
		String message = "æ›´æ–°æˆ?åŠŸ";
		try{
			jformOrderMainService.updateMain(jformOrderMain, jformOrderCustomerList,jformOrderTicketList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "æ›´æ–°è®¢å?•ä¸»ä¿¡æ?¯å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * è®¢å?•ä¸»ä¿¡æ?¯æ–°å¢žé¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(JformOrderMainEntity jformOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jformOrderMain.getId())) {
			jformOrderMain = jformOrderMainService.getEntity(JformOrderMainEntity.class, jformOrderMain.getId());
			req.setAttribute("jformOrderMainPage", jformOrderMain);
		}
		return new ModelAndView("com/jeecg/demo/orderOne2Many/jformOrderMain-add");
	}
	
	/**
	 * è®¢å?•ä¸»ä¿¡æ?¯ç¼–è¾‘é¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(JformOrderMainEntity jformOrderMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jformOrderMain.getId())) {
			jformOrderMain = jformOrderMainService.getEntity(JformOrderMainEntity.class, jformOrderMain.getId());
			req.setAttribute("jformOrderMainPage", jformOrderMain);
		}
		return new ModelAndView("com/jeecg/demo/orderOne2Many/jformOrderMain-update");
	}
	
	
	/**
	 * åŠ è½½æ˜Žç»†åˆ—è¡¨[JformOrderMainå­?è¡¨]
	 * 
	 * @return
	 */
	@RequestMapping(params = "jformOrderCustomerList")
	public ModelAndView jformOrderCustomerList(JformOrderMainEntity jformOrderMain, HttpServletRequest req) {
	
		//===================================================================================
		//èŽ·å?–å?‚æ•°
		Object id0 = jformOrderMain.getId();
		//===================================================================================
		//æŸ¥è¯¢-JformOrderMainå­?è¡¨
	    String hql0 = "from JformOrderCustomerEntity where 1 = 1 AND fK_ID = ? ";
	    try{
	    	List<JformOrderCustomerEntity> jformOrderCustomerEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("jformOrderCustomerList", jformOrderCustomerEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/demo/orderOne2Many/jformOrderCustomerList");
	}
	/**
	 * åŠ è½½æ˜Žç»†åˆ—è¡¨[JformOrderMainå­?è¡¨]
	 * 
	 * @return
	 */
	@RequestMapping(params = "jformOrderTicketList")
	public ModelAndView jformOrderTicketList(JformOrderMainEntity jformOrderMain, HttpServletRequest req) {
	
		//===================================================================================
		//èŽ·å?–å?‚æ•°
		Object id1 = jformOrderMain.getId();
		//===================================================================================
		//æŸ¥è¯¢-JformOrderMainå­?è¡¨
	    String hql1 = "from JformOrderTicketEntity where 1 = 1 AND fCK_ID = ? ";
	    try{
	    	List<JformOrderTicketEntity> jformOrderTicketEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("jformOrderTicketList", jformOrderTicketEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/demo/orderOne2Many/jformOrderTicketList");
	}

    /**
    * å¯¼å‡ºexcel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(JformOrderMainEntity jformOrderMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(JformOrderMainEntity.class, dataGrid);
    	//æŸ¥è¯¢æ?¡ä»¶ç»„è£…å™¨
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jformOrderMain);
    	try{
    	//è‡ªå®šä¹‰è¿½åŠ æŸ¥è¯¢æ?¡ä»¶
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<JformOrderMainEntity> list=this.jformOrderMainService.getListByCriteriaQuery(cq, false);
    	List<JformOrderMainPage> pageList=new ArrayList<JformOrderMainPage>();
        if(list!=null&&list.size()>0){
        	for(JformOrderMainEntity entity:list){
        		try{
        		JformOrderMainPage page=new JformOrderMainPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from JformOrderCustomerEntity where 1 = 1 AND fK_ID = ? ";
        	        List<JformOrderCustomerEntity> jformOrderCustomerEntityList = systemService.findHql(hql0,id0);
            		page.setJformOrderCustomerList(jformOrderCustomerEntityList);
            	    Object id1 = entity.getId();
				    String hql1 = "from JformOrderTicketEntity where 1 = 1 AND fCK_ID = ? ";
        	        List<JformOrderTicketEntity> jformOrderTicketEntityList = systemService.findHql(hql1,id1);
            		page.setJformOrderTicketList(jformOrderTicketEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"è®¢å?•ä¸»ä¿¡æ?¯");
        map.put(NormalExcelConstants.CLASS,JformOrderMainPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("è®¢å?•ä¸»ä¿¡æ?¯åˆ—è¡¨", "å¯¼å‡ºäºº:Jeecg",
            "å¯¼å‡ºä¿¡æ?¯"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * é€šè¿‡excelå¯¼å…¥æ•°æ?®
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// èŽ·å?–ä¸Šä¼ æ–‡ä»¶å¯¹è±¡
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<JformOrderMainPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), JformOrderMainPage.class, params);
				JformOrderMainEntity entity1=null;
				for (JformOrderMainPage page : list) {
					entity1=new JformOrderMainEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            jformOrderMainService.addMain(entity1, page.getJformOrderCustomerList(),page.getJformOrderTicketList());
				}
				j.setMsg("æ–‡ä»¶å¯¼å…¥æˆ?åŠŸï¼?");
			} catch (Exception e) {
				j.setMsg("æ–‡ä»¶å¯¼å…¥å¤±è´¥ï¼?");
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
	/**
	* å¯¼å‡ºexcel ä½¿æ¨¡æ?¿
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"è®¢å?•ä¸»ä¿¡æ?¯");
		map.put(NormalExcelConstants.CLASS,JformOrderMainPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("è®¢å?•ä¸»ä¿¡æ?¯åˆ—è¡¨", "å¯¼å‡ºäºº:"+ ResourceUtil.getSessionUser().getRealName(),
		"å¯¼å‡ºä¿¡æ?¯"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* å¯¼å…¥åŠŸèƒ½è·³è½¬
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "jformOrderMainController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="è®¢å?•åˆ—è¡¨ä¿¡æ?¯",produces="application/json",httpMethod="GET")

 	public List<JformOrderMainPage> list() {
		List<JformOrderMainEntity> list= jformOrderMainService.getList(JformOrderMainEntity.class);
    	List<JformOrderMainPage> pageList=new ArrayList<JformOrderMainPage>();
        if(list!=null&&list.size()>0){
        	for(JformOrderMainEntity entity:list){
        		try{
        			JformOrderMainPage page=new JformOrderMainPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object id0 = entity.getId();
					Object id1 = entity.getId();
				     String hql0 = "from JformOrderCustomerEntity where 1 = 1 AND fK_ID = ? ";
	    			List<JformOrderCustomerEntity> jformOrderCustomerOldList = this.jformOrderMainService.findHql(hql0,id0);
            		page.setJformOrderCustomerList(jformOrderCustomerOldList);
				     String hql1 = "from JformOrderTicketEntity where 1 = 1 AND fCK_ID = ? ";
	    			List<JformOrderTicketEntity> jformOrderTicketOldList = this.jformOrderMainService.findHql(hql1,id1);
            		page.setJformOrderTicketList(jformOrderTicketOldList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
		return pageList;

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="æ ¹æ?®IDèŽ·å?–è®¢å?•ä¿¡æ?¯",notes="æ ¹æ?®IDèŽ·å?–è®¢å?•ä¿¡æ?¯",httpMethod="GET",produces="application/json")
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		JformOrderMainEntity task = jformOrderMainService.get(JformOrderMainEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		JformOrderMainPage page = new JformOrderMainPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
			String hql0 = "from JformOrderCustomerEntity where 1 = 1 AND fK_ID = ? ";
 			List<JformOrderCustomerEntity> jformOrderCustomerOldList = this.jformOrderMainService.findHql(hql0,id);
     		page.setJformOrderCustomerList(jformOrderCustomerOldList);
			String hql1 = "from JformOrderTicketEntity where 1 = 1 AND fCK_ID = ? ";
 			List<JformOrderTicketEntity> jformOrderTicketOldList = this.jformOrderMainService.findHql(hql1,id);
     		page.setJformOrderTicketList(jformOrderTicketOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(page, HttpStatus.OK);

	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="åˆ›å»ºè®¢å?•")
	public ResponseEntity<?> create(@ApiParam(value="è®¢å?•ä¿¡æ?¯")@RequestBody JformOrderMainPage jformOrderMainPage, UriComponentsBuilder uriBuilder) {
		//è°ƒç”¨JSR303 Bean Validatorè¿›è¡Œæ ¡éªŒï¼Œå¦‚æžœå‡ºé”™è¿”å›žå?«400é”™è¯¯ç ?å?Šjsonæ ¼å¼?çš„é”™è¯¯ä¿¡æ?¯.
		Set<ConstraintViolation<JformOrderMainPage>> failures = validator.validate(jformOrderMainPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//ä¿?å­˜
		List<JformOrderCustomerEntity> jformOrderCustomerList =  jformOrderMainPage.getJformOrderCustomerList();
		List<JformOrderTicketEntity> jformOrderTicketList =  jformOrderMainPage.getJformOrderTicketList();
		
		JformOrderMainEntity jformOrderMain = new JformOrderMainEntity();
		try{

			MyBeanUtils.copyBeanNotNull2Bean(jformOrderMainPage,jformOrderMain);

		}catch(Exception e){
            logger.info(e.getMessage());
        }
		jformOrderMainService.addMain(jformOrderMain, jformOrderCustomerList,jformOrderTicketList);

		//æŒ‰ç…§Restfulé£Žæ ¼çº¦å®šï¼Œåˆ›å»ºæŒ‡å?‘æ–°ä»»åŠ¡çš„url, ä¹Ÿå?¯ä»¥ç›´æŽ¥è¿”å›židæˆ–å¯¹è±¡.
		String id = jformOrderMainPage.getId();
		URI uri = uriBuilder.path("/rest/jformOrderMainController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="æ›´æ–°è®¢å?•",notes="æ›´æ–°è®¢å?•")
	public ResponseEntity<?> update(@ApiParam(value="è®¢å?•ä¿¡æ?¯")@RequestBody JformOrderMainPage jformOrderMainPage) {
		//è°ƒç”¨JSR303 Bean Validatorè¿›è¡Œæ ¡éªŒï¼Œå¦‚æžœå‡ºé”™è¿”å›žå?«400é”™è¯¯ç ?å?Šjsonæ ¼å¼?çš„é”™è¯¯ä¿¡æ?¯.
		Set<ConstraintViolation<JformOrderMainPage>> failures = validator.validate(jformOrderMainPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//ä¿?å­˜
		List<JformOrderCustomerEntity> jformOrderCustomerList =  jformOrderMainPage.getJformOrderCustomerList();
		List<JformOrderTicketEntity> jformOrderTicketList =  jformOrderMainPage.getJformOrderTicketList();
		
		JformOrderMainEntity jformOrderMain = new JformOrderMainEntity();
		try{

			MyBeanUtils.copyBeanNotNull2Bean(jformOrderMainPage,jformOrderMain);

		}catch(Exception e){
            logger.info(e.getMessage());
        }
		jformOrderMainService.updateMain(jformOrderMain, jformOrderCustomerList,jformOrderTicketList);

		//æŒ‰Restfulçº¦å®šï¼Œè¿”å›ž204çŠ¶æ€?ç ?, æ— å†…å®¹. ä¹Ÿå?¯ä»¥è¿”å›ž200çŠ¶æ€?ç ?.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="åˆ é™¤è®¢å?•")
	public void delete(@PathVariable("id") String id) {
		JformOrderMainEntity jformOrderMain = jformOrderMainService.get(JformOrderMainEntity.class, id);
		jformOrderMainService.delMain(jformOrderMain);
	}
	/**
	 * èŽ·å?–æ–‡ä»¶é™„ä»¶ä¿¡æ?¯
	 * 
	 * @param id jformOrderMainä¸»é”®id
	 */
	@RequestMapping(params = "getFiles")
	@ResponseBody
	public AjaxJson getFiles(String id){
		List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
		List<Map<String,Object>> files = new ArrayList<Map<String,Object>>(0);
		for(CgUploadEntity b:uploadBeans){
			String title = b.getAttachmenttitle();//é™„ä»¶å??
			String fileKey = b.getId();//é™„ä»¶ä¸»é”®
			String path = b.getRealpath();//é™„ä»¶è·¯å¾„
			String field = b.getCgformField();//è¡¨å?•ä¸­ä½œä¸ºé™„ä»¶æŽ§ä»¶çš„å­—æ®µ
			Map<String, Object> file = new HashMap<String, Object>();
			file.put("title", title);
			file.put("fileKey", fileKey);
			file.put("path", path);
			file.put("field", field==null?"":field);
			files.add(file);
		}
		AjaxJson j = new AjaxJson();
		j.setObj(files);
		return j;
	}

	/**
	 * è¡Œç¼–è¾‘ä¿?å­˜æ“?ä½œ
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(JformOrderCustomerPage page){
		String message = "æ“?ä½œæˆ?åŠŸï¼?";
		List<JformOrderCustomerEntity> demos=page.getDemos();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(demos)){
			for(JformOrderCustomerEntity jeecgDemo:demos){
				if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
					JformOrderCustomerEntity t =this.systemService.get(JformOrderCustomerEntity.class, jeecgDemo.getId());
					try {
						MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
						systemService.saveOrUpdate(t);
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						//jeecgDemo.setStatus("0");
						systemService.save(jeecgDemo);
						systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		return j;
	}
	
	/**
	 * æ ‘é€‰æ‹©é¡µé?¢è·³è½¬
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "departSelect")
    public String departSelect(HttpServletRequest req) {
    	req.setAttribute("defaultName", req.getParameter("name"));
        return "com/jeecg/demo/orderOne2Many/departSelect";
    }
	
	/**
	 * æ ‘åŠ è½½
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "getDepartInfo3")
	@ResponseBody
	public AjaxJson getDepartInfo3(HttpServletRequest request, HttpServletResponse response){
		AjaxJson j = new AjaxJson();
		net.sf.json.JSONArray jsonArray = new net.sf.json.JSONArray();
		//String parentid = request.getParameter("parentid");
		String sql = "select id,departname as name,ifnull(parentdepartid,0) as ppp_id,org_code as code from t_s_depart where 1=1 ";
		List<Map<String,Object>> dateList = this.systemService.findForJdbc(sql);
		Map<String,Map<String,Object>> dataMap = new HashMap<String,Map<String,Object>>();
		//TODO ä¸?åº”è¯¥æ¯?æ¬¡éƒ½éœ€è¦?æŸ¥è¯¢ å»ºè®®ä»Žç¼“å­˜ä¸­å?–åˆ°æ‰€æœ‰çš„list
		String name = request.getParameter("name");
		if(oConvertUtils.isNotEmpty(name)){
			for (Map<String, Object> map : dateList) {
				String temp = map.get("name").toString();
				String id = map.get("id").toString();
				if(temp.indexOf(name)>=0){
					Object pid = map.get("ppp_id");
					if(temp.equals(name)){
						map.put("checked", true);
					}
					//åˆ¤æ–­æ˜¯å?¦æœ‰å­?èŠ‚ç‚¹ å?¯ç”¨isleafåˆ¤æ–­
					sql = "select count(1) from t_s_depart t where t.parentdepartid = ?";
					long count = this.systemService.getCountForJdbcParam(sql, new Object[]{id});
					if(count>0){
						map.put("isParent",true);
					}
					dataMap.put(id, map);
					upwardQueryParents(dataMap, dateList, pid==null?"":pid.toString());
				}
			}
			jsonArray =  net.sf.json.JSONArray.fromObject(dataMap.values());
		}else{
			jsonArray =  net.sf.json.JSONArray.fromObject(dateList);
		}
		
		j.setMsg(jsonArray.toString().replace("ppp_id", "pId"));
		return j;
	}
	
	/**
	 * èŽ·å?–å­?èŠ‚ç‚¹
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "getSubContent")
	@ResponseBody
	public AjaxJson getSubContent(HttpServletRequest request, HttpServletResponse response){
		
		AjaxJson j = new AjaxJson();
		String parentid = request.getParameter("parentid");
		List<TSDepart> tSDeparts = new ArrayList<TSDepart>();
		StringBuffer hql = new StringBuffer(" from TSDepart t where 1=1 ");
		if(oConvertUtils.isNotEmpty(parentid)){
			TSDepart dePart = this.systemService.getEntity(TSDepart.class, parentid);
			hql.append(" and TSPDepart = ?");
			tSDeparts = this.systemService.findHql(hql.toString(), dePart);
		}
		//TODO ä¸?åº”è¯¥æ¯?æ¬¡éƒ½éœ€è¦?æŸ¥è¯¢ å»ºè®®ä»Žç¼“å­˜ä¸­å?–åˆ°æ‰€æœ‰çš„list å†?ç­›é€‰
		List<Map<String,Object>> dateList = new ArrayList<Map<String,Object>>();
		if(tSDeparts.size()>0){
			Map<String,Object> map = null;
			String sql = null;
			 Object[] params = null;
			for(TSDepart depart:tSDeparts){
				map = new HashMap<String,Object>();
				map.put("id", depart.getId());
				map.put("name", depart.getDepartname());

				map.put("code",depart.getOrgCode());

				TSDepart pdepart = depart.getTSPDepart();
				if(pdepart!=null){
					map.put("pId", pdepart.getId());
				} else{
					map.put("pId", "0");
				}
				//æ ¹æ?®idåˆ¤æ–­æ˜¯å?¦æœ‰å­?èŠ‚ç‚¹
				sql = "select count(1) from t_s_depart t where t.parentdepartid = ?";
				params = new Object[]{depart.getId()};
				long count = this.systemService.getCountForJdbcParam(sql, params);
				if(count>0){
					map.put("isParent",true);
				}
				dateList.add(map);
			}
		}
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(dateList);
		j.setMsg(jsonArray.toString());
		return j;
	}
	
	/**
	 * å?‘ä¸ŠæŸ¥æ‰¾çˆ¶èŠ‚ç‚¹
	 */
	private void upwardQueryParents(Map<String,Map<String,Object>> dataMap,List<Map<String,Object>> dateList,String pid){
		String pid_next = null;
		for (Map<String, Object> map : dateList) {
			String id = map.get("id").toString();
			if(pid.equals(id)){
				pid_next = map.get("ppp_id").toString();
				dataMap.put(id, map);
				break;
			}
		}
		if(pid_next!=null && !pid_next.equals("0")){
			upwardQueryParents(dataMap, dateList, pid_next);
		}
	}

}
