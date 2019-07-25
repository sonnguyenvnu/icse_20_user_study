package com.jeecg.demo.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
import org.hibernate.criterion.Restrictions;
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
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.jwt.web.TokenController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
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
import com.jeecg.demo.entity.JfromOrderEntity;
import com.jeecg.demo.entity.JfromOrderLineEntity;
import com.jeecg.demo.page.JfromOrderPage;
import com.jeecg.demo.service.JfromOrderServiceI;

import org.jeecgframework.web.superquery.util.SuperQueryUtil;

/**   
 * @Title: Controller
 * @Description: 订�?�列表
 * @author onlineGenerator
 * @date 2017-12-14 13:36:56
 * @version V1.0   
 *
 */
//@Api(value="JfromOrder",description="订�?�列表",tags="jfromOrderController")
@Controller
@RequestMapping("/jfromOrderController")
public class JfromOrderController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(JfromOrderController.class);

	@Autowired
	private JfromOrderServiceI jfromOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 订�?�列表列表 页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/jfromOrderList");
	}

	/**
	 * 订�?�列表列表 页�?�跳转 (自定义�?表显示demo)
	 * 
	 * @return
	 */
	@RequestMapping(params = "gridViewlist")
	public ModelAndView gridViewlist(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/jfromOrderGridViewList");
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
	public void datagrid(JfromOrderEntity jfromOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JfromOrderEntity.class, dataGrid);
		//查询�?�件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jfromOrder);
		try{

			String sql = SuperQueryUtil.getComplxSuperQuerySQL(request);
			if(oConvertUtils.isNotEmpty(sql)) {
				cq.add(Restrictions.sqlRestriction(" id in ("+sql+")"));
			}

		//自定义追加查询�?�件
		}catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.jfromOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除订�?�列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(JfromOrderEntity jfromOrder, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jfromOrder = systemService.getEntity(JfromOrderEntity.class, jfromOrder.getId());
		String message = "订�?�列表删除�?功";
		try{
			jfromOrderService.delMain(jfromOrder);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订�?�列表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批�?删除订�?�列表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "订�?�列表删除�?功";
		try{
			for(String id:ids.split(",")){
				JfromOrderEntity jfromOrder = systemService.getEntity(JfromOrderEntity.class,
				id
				);
				jfromOrderService.delMain(jfromOrder);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "订�?�列表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加订�?�列表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(JfromOrderEntity jfromOrder,JfromOrderPage jfromOrderPage, HttpServletRequest request) {
		List<JfromOrderLineEntity> jfromOrderLineList =  jfromOrderPage.getJfromOrderLineList();
		AjaxJson j = new AjaxJson();
		String message = "添加�?功";
		try{
			jfromOrderService.addMain(jfromOrder, jfromOrderLineList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "订�?�列表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新订�?�列表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(JfromOrderEntity jfromOrder,JfromOrderPage jfromOrderPage, HttpServletRequest request) {
		List<JfromOrderLineEntity> jfromOrderLineList =  jfromOrderPage.getJfromOrderLineList();
		AjaxJson j = new AjaxJson();
		String message = "更新�?功";
		try{
			jfromOrderService.updateMain(jfromOrder, jfromOrderLineList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新订�?�列表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 订�?�列表新增页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(JfromOrderEntity jfromOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jfromOrder.getId())) {
			jfromOrder = jfromOrderService.getEntity(JfromOrderEntity.class, jfromOrder.getId());
			req.setAttribute("jfromOrderPage", jfromOrder);
		}
		return new ModelAndView("com/jeecg/demo/jfromOrder-add");
	}
	
	/**
	 * 订�?�列表编辑页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(JfromOrderEntity jfromOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jfromOrder.getId())) {
			jfromOrder = jfromOrderService.getEntity(JfromOrderEntity.class, jfromOrder.getId());
			req.setAttribute("jfromOrderPage", jfromOrder);
		}
		return new ModelAndView("com/jeecg/demo/jfromOrder-update");
	}
	
	
	/**
	 * 加载明细列表[订�?�表体]
	 * 
	 * @return
	 */
	@RequestMapping(params = "jfromOrderLineList")
	public ModelAndView jfromOrderLineList(JfromOrderEntity jfromOrder, HttpServletRequest req) {
	
		//===================================================================================
		//获�?��?�数
		Object id0 = jfromOrder.getId();
		//===================================================================================
		//查询-订�?�表体
	    String hql0 = "from JfromOrderLineEntity where 1 = 1 AND oRDERID = ? ";
	    try{
	    	List<JfromOrderLineEntity> jfromOrderLineEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("jfromOrderLineList", jfromOrderLineEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/jeecg/demo/jfromOrderLineList");
	}

	@RequestMapping(params = "jfromOrderLineDatagrid")
	public void jfromOrderLineDatagrid(JfromOrderLineEntity jfromOrderLineEntity,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JfromOrderLineEntity.class, dataGrid);
		//查询�?�件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jfromOrderLineEntity);
		cq.add();
		this.jfromOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(JfromOrderEntity jfromOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(JfromOrderEntity.class, dataGrid);
    	//查询�?�件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jfromOrder);
    	try{
    	//自定义追加查询�?�件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<JfromOrderEntity> list=this.jfromOrderService.getListByCriteriaQuery(cq, false);
    	List<JfromOrderPage> pageList=new ArrayList<JfromOrderPage>();
        if(list!=null&&list.size()>0){
        	for(JfromOrderEntity entity:list){
        		try{
        		JfromOrderPage page=new JfromOrderPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from JfromOrderLineEntity where 1 = 1 AND oRDERID = ? ";
        	        List<JfromOrderLineEntity> jfromOrderLineEntityList = systemService.findHql(hql0,id0);
            		page.setJfromOrderLineList(jfromOrderLineEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"订�?�列表");
        map.put(NormalExcelConstants.CLASS,JfromOrderPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("订�?�列表列表", "导出人:Jeecg",
            "导出信�?�"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数�?�
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
			MultipartFile file = entity.getValue();// 获�?�上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<JfromOrderPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), JfromOrderPage.class, params);
				JfromOrderEntity entity1=null;
				for (JfromOrderPage page : list) {
					entity1=new JfromOrderEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            jfromOrderService.addMain(entity1, page.getJfromOrderLineList());
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
	/**
	* 导出excel 使模�?�
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"订�?�列表");
		map.put(NormalExcelConstants.CLASS,JfromOrderPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("订�?�列表列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
		"导出信�?�"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "jfromOrderController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	//@ApiOperation(value="订�?�列表列表信�?�",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<JfromOrderPage>> list() {
		List<JfromOrderEntity> list= jfromOrderService.getList(JfromOrderEntity.class);
    	List<JfromOrderPage> pageList=new ArrayList<JfromOrderPage>();
        if(list!=null&&list.size()>0){
        	for(JfromOrderEntity entity:list){
        		try{
        			JfromOrderPage page=new JfromOrderPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object id0 = entity.getId();
				     String hql0 = "from JfromOrderLineEntity where 1 = 1 AND oRDERID = ? ";
	    			List<JfromOrderLineEntity> jfromOrderLineOldList = this.jfromOrderService.findHql(hql0,id0);
            		page.setJfromOrderLineList(jfromOrderLineOldList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
		return Result.success(pageList);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	//@ApiOperation(value="根�?�ID获�?�订�?�列表信�?�",notes="根�?�ID获�?�订�?�列表信�?�",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		JfromOrderEntity task = jfromOrderService.get(JfromOrderEntity.class, id);
		if (task == null) {
			return Result.error("根�?�ID获�?�订�?�列表信�?�为空");
		}
		JfromOrderPage page = new JfromOrderPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object id0 = task.getId();
		    String hql0 = "from JfromOrderLineEntity where 1 = 1 AND oRDERID = ? ";
			List<JfromOrderLineEntity> jfromOrderLineOldList = this.jfromOrderService.findHql(hql0,id0);
    		page.setJfromOrderLineList(jfromOrderLineOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	//@ApiOperation(value="创建订�?�列表")
	public ResponseMessage<?> create(@ApiParam(name="订�?�列表对象")@RequestBody JfromOrderPage jfromOrderPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回�?�400错误�?�?�json格�?的错误信�?�.
		Set<ConstraintViolation<JfromOrderPage>> failures = validator.validate(jfromOrderPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//�?存
		List<JfromOrderLineEntity> jfromOrderLineList =  jfromOrderPage.getJfromOrderLineList();
		
		JfromOrderEntity jfromOrder = new JfromOrderEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(jfromOrderPage,jfromOrder);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("�?存订�?�列表失败");
        }
		jfromOrderService.addMain(jfromOrder, jfromOrderLineList);

		return Result.success(jfromOrder);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	//@ApiOperation(value="更新订�?�列表",notes="更新订�?�列表")
	public ResponseMessage<?> update(@RequestBody JfromOrderPage jfromOrderPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回�?�400错误�?�?�json格�?的错误信�?�.
		Set<ConstraintViolation<JfromOrderPage>> failures = validator.validate(jfromOrderPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//�?存
		List<JfromOrderLineEntity> jfromOrderLineList =  jfromOrderPage.getJfromOrderLineList();
		
		JfromOrderEntity jfromOrder = new JfromOrderEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(jfromOrderPage,jfromOrder);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("订�?�列表更新失败");
        }
		jfromOrderService.updateMain(jfromOrder, jfromOrderLineList);

		//按Restful约定，返回204状�?�?, 无内容. 也�?�以返回200状�?�?.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	//@ApiOperation(value="删除订�?�列表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" , id);
		// 验�?
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID�?能为空");
		}
		try {
			JfromOrderEntity jfromOrder = jfromOrderService.get(JfromOrderEntity.class, id);
			jfromOrderService.delMain(jfromOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("订�?�列表删除失败");
		}

		return Result.success();
	}
}
