package org.jeecgframework.web.system.controller.core;
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSCompanyPositionEntity;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUserPositionRelEntity;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.TSCompanyPositionServiceI;
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

/**   
 * @Title: Controller  
 * @Description: è?ŒåŠ¡ç®¡ç?†
 * @author onlineGenerator
 * @date 2017-11-07 16:21:23
 * @version V1.0   
 *
 */
//@Api(value="TSCompanyPosition",description="è?ŒåŠ¡ç®¡ç?†",tags="tSCompanyPositionController")
@Controller
@RequestMapping("/tSCompanyPositionController")
public class TSCompanyPositionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TSCompanyPositionController.class);

	@Autowired
	private TSCompanyPositionServiceI tSCompanyPositionService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * è?ŒåŠ¡ç®¡ç?†åˆ—è¡¨ é¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		String companyId = request.getParameter("companyId");
		request.setAttribute("companyId", companyId);
		return new ModelAndView("system/position/tSCompanyPositionList");
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
	public void datagrid(TSCompanyPositionEntity tSCompanyPosition,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSCompanyPositionEntity.class, dataGrid);
		//æŸ¥è¯¢æ?¡ä»¶ç»„è£…å™¨
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSCompanyPosition, request.getParameterMap());
		try{
		//è‡ªå®šä¹‰è¿½åŠ æŸ¥è¯¢æ?¡ä»¶
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tSCompanyPositionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * åˆ é™¤è?ŒåŠ¡ç®¡ç?†
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TSCompanyPositionEntity tSCompanyPosition, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tSCompanyPosition = systemService.getEntity(TSCompanyPositionEntity.class, tSCompanyPosition.getId());
		message = "è?ŒåŠ¡ç®¡ç?†åˆ é™¤æˆ?åŠŸ";
		try{
			tSCompanyPositionService.delete(tSCompanyPosition);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "è?ŒåŠ¡ç®¡ç?†åˆ é™¤å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * æ‰¹é‡?åˆ é™¤è?ŒåŠ¡ç®¡ç?†
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "è?ŒåŠ¡ç®¡ç?†åˆ é™¤æˆ?åŠŸ";
		try{
			for(String id:ids.split(",")){
				TSCompanyPositionEntity tSCompanyPosition = systemService.getEntity(TSCompanyPositionEntity.class, 
				id
				);
				tSCompanyPositionService.delete(tSCompanyPosition);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "è?ŒåŠ¡ç®¡ç?†åˆ é™¤å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * æ·»åŠ è?ŒåŠ¡ç®¡ç?†
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TSCompanyPositionEntity tSCompanyPosition, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "è?ŒåŠ¡ç®¡ç?†æ·»åŠ æˆ?åŠŸ";
		try{
			tSCompanyPositionService.save(tSCompanyPosition);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "è?ŒåŠ¡ç®¡ç?†æ·»åŠ å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * æ›´æ–°è?ŒåŠ¡ç®¡ç?†
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TSCompanyPositionEntity tSCompanyPosition, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "è?ŒåŠ¡ç®¡ç?†æ›´æ–°æˆ?åŠŸ";
		TSCompanyPositionEntity t = tSCompanyPositionService.get(TSCompanyPositionEntity.class, tSCompanyPosition.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tSCompanyPosition, t);
			tSCompanyPositionService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "è?ŒåŠ¡ç®¡ç?†æ›´æ–°å¤±è´¥";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * è?ŒåŠ¡ç®¡ç?†æ–°å¢žé¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TSCompanyPositionEntity tSCompanyPosition, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSCompanyPosition.getId())) {
			tSCompanyPosition = tSCompanyPositionService.getEntity(TSCompanyPositionEntity.class, tSCompanyPosition.getId());
		}
		req.setAttribute("tSCompanyPositionPage", tSCompanyPosition);
		return new ModelAndView("system/position/tSCompanyPosition-add");
	}
	/**
	 * è?ŒåŠ¡ç®¡ç?†ç¼–è¾‘é¡µé?¢è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TSCompanyPositionEntity tSCompanyPosition, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSCompanyPosition.getId())) {
			tSCompanyPosition = tSCompanyPositionService.getEntity(TSCompanyPositionEntity.class, tSCompanyPosition.getId());
			req.setAttribute("tSCompanyPositionPage", tSCompanyPosition);
		}
		return new ModelAndView("system/position/tSCompanyPosition-update");
	}
	
	/**
	 * å¯¼å…¥åŠŸèƒ½è·³è½¬
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","tSCompanyPositionController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * å¯¼å‡ºexcel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TSCompanyPositionEntity tSCompanyPosition,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TSCompanyPositionEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSCompanyPosition, request.getParameterMap());
		List<TSCompanyPositionEntity> tSCompanyPositions = this.tSCompanyPositionService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"è?ŒåŠ¡ç®¡ç?†");
		modelMap.put(NormalExcelConstants.CLASS,TSCompanyPositionEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("è?ŒåŠ¡ç®¡ç?†åˆ—è¡¨", "å¯¼å‡ºäºº:"+ResourceUtil.getSessionUser().getRealName(),
			"å¯¼å‡ºä¿¡æ?¯"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tSCompanyPositions);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * å¯¼å‡ºexcel ä½¿æ¨¡æ?¿
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TSCompanyPositionEntity tSCompanyPosition,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"è?ŒåŠ¡ç®¡ç?†");
    	modelMap.put(NormalExcelConstants.CLASS,TSCompanyPositionEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("è?ŒåŠ¡ç®¡ç?†åˆ—è¡¨", "å¯¼å‡ºäºº:"+ResourceUtil.getSessionUser().getRealName(),
    	"å¯¼å‡ºä¿¡æ?¯"));
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
			MultipartFile file = entity.getValue();// èŽ·å?–ä¸Šä¼ æ–‡ä»¶å¯¹è±¡
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<TSCompanyPositionEntity> listTSCompanyPositionEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TSCompanyPositionEntity.class,params);
				for (TSCompanyPositionEntity tSCompanyPosition : listTSCompanyPositionEntitys) {
					tSCompanyPositionService.save(tSCompanyPosition);
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
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
//	@ApiOperation(value="è?ŒåŠ¡ç®¡ç?†åˆ—è¡¨ä¿¡æ?¯",produces="application/json",httpMethod="GET")
	public List<TSCompanyPositionEntity> list() {
		List<TSCompanyPositionEntity> listTSCompanyPositions=tSCompanyPositionService.getList(TSCompanyPositionEntity.class);
		return listTSCompanyPositions;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
//	@ApiOperation(value="æ ¹æ?®IDèŽ·å?–è?ŒåŠ¡ç®¡ç?†ä¿¡æ?¯",notes="æ ¹æ?®IDèŽ·å?–è?ŒåŠ¡ç®¡ç?†ä¿¡æ?¯",httpMethod="GET",produces="application/json")
	public ResponseEntity<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		TSCompanyPositionEntity task = tSCompanyPositionService.get(TSCompanyPositionEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
//	@ApiOperation(value="åˆ›å»ºè?ŒåŠ¡ç®¡ç?†")
	public ResponseEntity<?> create(@ApiParam(name="è?ŒåŠ¡ç®¡ç?†å¯¹è±¡")@RequestBody TSCompanyPositionEntity tSCompanyPosition, UriComponentsBuilder uriBuilder) {
		//è°ƒç”¨JSR303 Bean Validatorè¿›è¡Œæ ¡éªŒï¼Œå¦‚æžœå‡ºé”™è¿”å›žå?«400é”™è¯¯ç ?å?Šjsonæ ¼å¼?çš„é”™è¯¯ä¿¡æ?¯.
		Set<ConstraintViolation<TSCompanyPositionEntity>> failures = validator.validate(tSCompanyPosition);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//ä¿?å­˜
		try{
			tSCompanyPositionService.save(tSCompanyPosition);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//æŒ‰ç…§Restfulé£Žæ ¼çº¦å®šï¼Œåˆ›å»ºæŒ‡å?‘æ–°ä»»åŠ¡çš„url, ä¹Ÿå?¯ä»¥ç›´æŽ¥è¿”å›židæˆ–å¯¹è±¡.
		String id = tSCompanyPosition.getId();
		URI uri = uriBuilder.path("/rest/tSCompanyPositionController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
//	@ApiOperation(value="æ›´æ–°ç”¨æˆ·ä¿¡æ?¯",notes="æ›´æ–°ç”¨æˆ·æ•°æ?®ä¿¡æ?¯")
	public ResponseEntity<?> update(@ApiParam(name="è?ŒåŠ¡ç®¡ç?†å¯¹è±¡")@RequestBody TSCompanyPositionEntity tSCompanyPosition) {
		//è°ƒç”¨JSR303 Bean Validatorè¿›è¡Œæ ¡éªŒï¼Œå¦‚æžœå‡ºé”™è¿”å›žå?«400é”™è¯¯ç ?å?Šjsonæ ¼å¼?çš„é”™è¯¯ä¿¡æ?¯.
		Set<ConstraintViolation<TSCompanyPositionEntity>> failures = validator.validate(tSCompanyPosition);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//ä¿?å­˜
		try{
			tSCompanyPositionService.saveOrUpdate(tSCompanyPosition);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//æŒ‰Restfulçº¦å®šï¼Œè¿”å›ž204çŠ¶æ€?ç ?, æ— å†…å®¹. ä¹Ÿå?¯ä»¥è¿”å›ž200çŠ¶æ€?ç ?.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
//	@ApiOperation(value="åˆ é™¤ç”¨æˆ·ä¿¡æ?¯")
	public void delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		tSCompanyPositionService.deleteEntityById(TSCompanyPositionEntity.class, id);
	}
	
	
	/**
	 * åŠ è½½è?ŒåŠ¡zTree
	 * @param functionGroup
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(params="getTreeData",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<Map<String,Object>> getTreeData(HttpServletResponse response,HttpServletRequest request ){
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		try{
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String level = request.getParameter("level");
			String departid = request.getParameter("departid");
			String userId = request.getParameter("userId");
			logger.info("------id----"+id+"----name----"+name+"----level-----"+level+"---departid---"+departid+"----userId---"+userId);
		    //æŸ¥æ‰¾ç›´æŽ¥ä¸Šçº§å…¬å?¸
			String companyId = getCompanyId(departid);
			if(StringUtils.isEmpty(companyId)){
				return dataList;
			}
			
			//æ ¹æ?®å…¬å?¸idæŸ¥è¯¢ï¼Œè¯¥å…¬å?¸ä¸‹çš„è?ŒåŠ¡
			List<TSCompanyPositionEntity> list = tSCompanyPositionService.findByProperty(TSCompanyPositionEntity.class, "companyId", companyId);
			
			//æ ¹æ?®ç”¨æˆ·idå’Œå…¬å?¸idæŸ¥è¯¢ç”¨æˆ·ç®¡ç?†çš„è?ŒåŠ¡
			String hql = "select up from TSUserPositionRelEntity up,TSCompanyPositionEntity p where  p.companyId = up.companyId " +
					" and up.companyId = ? and up.userId = ?";
			List<TSUserPositionRelEntity> selectlist = tSCompanyPositionService.findHql(hql, companyId,userId);
			populateTree(list,selectlist,dataList);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return dataList;
	}
	
	private String getCompanyId(String departid){
		TSDepart depart= this.systemService.findUniqueByProperty(TSDepart.class, "id", departid);
		if(depart!=null&&("1".equals(depart.getOrgType())||"4".equals(depart.getOrgType()))){
			return depart.getId();
		}else{
			if(depart.getTSPDepart()!=null){
				return getCompanyId(depart.getTSPDepart().getId());
			}
		}
		return null;
	}
	
	private void populateTree(List<TSCompanyPositionEntity> list,List<TSUserPositionRelEntity> selectlist,List<Map<String,Object>> dataList){
		Map<String,Object> map = null;
		if(list!=null&&list.size()>0){
			for(TSCompanyPositionEntity companyPositionEntity :list){
				map = new HashMap<String,Object>();
				map.put("open", false);
				map.put("id", companyPositionEntity.getId());
				map.put("name", companyPositionEntity.getPositionName());
				if(selectlist!=null&&selectlist.size()>0){
					for(TSUserPositionRelEntity selectCompanyPosition:selectlist){
						if(companyPositionEntity.getId().equals(selectCompanyPosition.getPositionId())){
							map.put("checked",true);
							break;
						}
					}
				}
				map.put("icon","plug-in/ztree/css/img/diy/zhiwu.png");
				map.put("isParent", false);
				map.put("parentId","0");
				dataList.add(map);
			}
		}
		
	}
	
	/**
	 * ä¿?å­˜ç”¨æˆ·å…¬å?¸è?ŒåŠ¡å…³è?”æ•°æ?®
	 * @param functionGroupUser
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "saveUserCompanyPosition",method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveUserCompanyPosition(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String departid = request.getParameter("departid");
		String userId = request.getParameter("userId");
		String positionIds = request.getParameter("positionIds");
		try {
			if(StringUtils.isNotEmpty(departid) 
					&& StringUtils.isNotEmpty(userId)) {
				
				 //æŸ¥æ‰¾ç›´æŽ¥ä¸Šçº§å…¬å?¸
				String companyId = getCompanyId(departid);
				if(StringUtils.isEmpty(companyId)){
					return j;
				}
				//åˆ é™¤æ‰€æœ‰å…³è?”
				String sql = "delete from t_s_user_position_rel where user_id = ? and company_id = ?";
				systemService.executeSql(sql, userId,companyId);
				//ä¿?å­˜æ•°æ?®
				if(StringUtils.isEmpty(positionIds)){
					return j;
				}
				String[] positionIdArr = positionIds.split(",");
				TSUserPositionRelEntity userPositionRelEntity = null;
				for (String positionId : positionIdArr) {
					userPositionRelEntity = new TSUserPositionRelEntity();
					userPositionRelEntity.setCompanyId(companyId);
					userPositionRelEntity.setPositionId(positionId);
					userPositionRelEntity.setUserId(userId);
					systemService.save(userPositionRelEntity);
				}
//				updateGroupUser(set, userName, map);
				j.setMsg("åˆ†é…?è?ŒåŠ¡æˆ?åŠŸ");
			}else{
				j.setMsg("åˆ†é…?è?ŒåŠ¡å¤±è´¥");
			}
		} catch(Exception e) {
			e.printStackTrace();
			j.setMsg("åˆ†é…?è?ŒåŠ¡å¤±è´¥");
			j.setSuccess(false);
		}
		return j;
	}
}
