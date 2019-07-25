package com.jeecg.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.StoreUploadFilePathEnum;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.HttpRequest;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.demo.dao.JeecgMinidaoDao;
import com.jeecg.demo.entity.TSDocument;

/**
 * @ClassName: JeecgFormDemoController
 * @Description: TODO(演示例�?处�?�类)
 * @author jeecg
 */
@Controller
@RequestMapping("/jeecgFormDemoController")
public class JeecgFormDemoController extends BaseController {
	private static final Logger logger = Logger.getLogger(JeecgFormDemoController.class);
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private JeecgMinidaoDao jeecgMinidaoDao;
	
	@RequestMapping(params = "uitag")
	public ModelAndView uitag(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/form_uitag");
	}

	/**
	 * 二维�?生�?功能
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "qrcode")
	public ModelAndView qrcode(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/form_QRCode");
	}

	@RequestMapping(params = "formValidDemo")
	public ModelAndView formValidDemo(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/form_valid");
	}

	@RequestMapping(params = "testsubmit=1",method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testsubmit(HttpServletRequest request) {
		logger.info("请求�?功byebye");
		return new ModelAndView("com/jeecg/demo/form_valid");
	}
	
	@RequestMapping(params = "nature")
	public ModelAndView easyDemo(HttpServletRequest request) {
		logger.info("demo-nature");
		//ztree�?�步加载
		JSONArray jsonArray=JSONArray.fromObject(getZtreeData());
		request.setAttribute("regions", jsonArray.toString().replaceAll("pid","pId"));
		return new ModelAndView("com/jeecg/demo/form_nature");
	}

	@RequestMapping(params = "natures")
	public ModelAndView topjuiDemo(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/form_natures");
	}


	
	
	/**
	 * 动�?创建多tab demo，�?�考截图效果
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "ueditor")
	public ModelAndView ueditor(HttpServletRequest request) {
		logger.info("ueditor");
		return new ModelAndView("com/jeecg/demo/ueditor");
	}

	/**
	 * popup赋多个值 demo
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "popupMultiValue")
	public ModelAndView popupMultiValue(HttpServletRequest request) {
		logger.info("popupMultiValue");
		return new ModelAndView("com/jeecg/demo/form_popupMultiValue");
	}

	/**
	 *下拉�?�动数�?�---�?市区
	 */
	@RequestMapping(params="regionSelect",method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> cityselect(HttpServletRequest req) throws Exception{
		logger.info("----�?市区�?�动-----");
		String pid=req.getParameter("pid");
		List<Map<String, String>> list=jeecgMinidaoDao.getProCity(pid);
		return jeecgMinidaoDao.getProCity(pid);
	}
	
	/**
	 * Ztree
	 * 获�?�所有的�?市区数�?�
	 * @return
	 */
	public List<Map<String, String>> getZtreeData(){
		return jeecgMinidaoDao.getAllRegions();
	}
	
	/**
	 * 父级DEMO下拉�?��?�
	 */
	@RequestMapping(params = "getComboTreeData")
	@ResponseBody
	public List<ComboTree> getComboTreeData(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		if (comboTree.getId() != null) {
			cq.eq("TSPDepart.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSPDepart");
		}
		cq.add();
		List<TSDepart> demoList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "departname", "TSDeparts");
		comboTrees = systemService.ComboTree(demoList, comboTreeModel, null, false);
		return comboTrees;
	}
	
	/**
	 * 加载ztree
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(params="getTreeData",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson getTreeData(TSDepart depatr,HttpServletResponse response,HttpServletRequest request ){
		AjaxJson j = new AjaxJson();
		try{
			List<TSDepart> depatrList = new ArrayList<TSDepart>();
			StringBuffer hql = new StringBuffer(" from TSDepart t");
			//hql.append(" and (parent.id is null or parent.id='')");
			depatrList = this.systemService.findHql(hql.toString());
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = null;
			for (TSDepart tsdepart : depatrList) {
				String sqls = null;
				Object[] paramss = null;
				map = new HashMap<String,Object>();
				map.put("id", tsdepart.getId());
				map.put("name", tsdepart.getDepartname());
				if (tsdepart.getTSPDepart() != null) {
					map.put("pId", tsdepart.getTSPDepart().getId());
					map.put("open",false);
				}else {
					map.put("pId", "1");
					map.put("open",false);
				}
				sqls = "select count(1) from t_s_depart t where t.parentdepartid = ?";
				paramss = new Object[]{tsdepart.getId()};
				long counts = this.systemService.getCountForJdbcParam(sqls, paramss);
				if(counts>0){
					dataList.add(map);
				}else{
					TSDepart de = this.systemService.get(TSDepart.class, tsdepart.getId());
					if (de != null) {
						map.put("id", de.getId());
						map.put("name", de.getDepartname());
						if(tsdepart.getTSPDepart()!=null){
							map.put("pId", tsdepart.getTSPDepart().getId());
							map.put("open",false);
						}else{
							map.put("pId", "1");
							map.put("open",false);
						}
						dataList.add(map);
					}else{
						map.put("open",false);
						dataList.add(map);
					}
				}
			}
		j.setObj(dataList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return j;
	}
	
	
	/**
	 * 自动完�?请求返回数�?�
	 * @param request
	 * @param responss
	 */
	@RequestMapping(params = "getAutocompleteData",method ={RequestMethod.GET, RequestMethod.POST})
	public void getAutocompleteData(HttpServletRequest request, HttpServletResponse response) {
		String searchVal = request.getParameter("q");
		String hql = "from TSUser where userName like '%"+searchVal+"%'";
		List autoList = systemService.findHql(hql);
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(JSONHelper.listtojson(new String[]{"userName"},1,autoList));
            response.getWriter().flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
			}
		}

	}
	
	/**
	 *  �?demo】电�?签章
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "eSign")
	public ModelAndView eSignDemo(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/zsign");
	}

	/**
	 * 左�?�布局demo
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "siteSelect")
	public ModelAndView siteSelect(HttpServletRequest request) {
		logger.info("----左�?�布局 demo转入页�?�-----");
		return new ModelAndView("com/jeecg/demo/siteSelect");
	}	

	/**
	 * 上下特殊布局
	 */
	@RequestMapping(params = "specialLayout")
	public ModelAndView rowListDemo(HttpServletRequest request) {
		logger.info("----上下特殊布局 demo转入页�?�-----");
		return new ModelAndView("com/jeecg/demo/specialLayout");
	}
	
	/**
	 * 通用上传demo
	 * @return
	 */
	@RequestMapping(params = "commonUpload")
	public ModelAndView commonUploadDemo(){
		return new ModelAndView("system/commonupload/commonUploadFile");
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(params = "saveUploadFile")
	@ResponseBody
	public AjaxJson saveUploadFile(String documentTitle,String filename,String swfpath){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			if(StringUtil.isEmpty(filename)){
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg("未上传文件");
				return ajaxJson;
			}
			TSTypegroup tsTypegroup=systemService.getTypeGroup("fieltype","文档分类");
			TSType tsType = systemService.getType("files","附件", tsTypegroup);
			TSDocument document = new TSDocument();
			document.setDocumentTitle(documentTitle);
			document.setRealpath(filename);
			document.setSubclassname(MyClassLoader.getPackPath(document));
			document.setCreatedate(DateUtils.gettimestamp());
			document.setTSType(tsType);
			document.setSwfpath(swfpath);
			String fileName = filename.substring(filename.lastIndexOf("/")+1,filename.lastIndexOf("."));
			document.setAttachmenttitle(fileName);
			document.setExtend(filename.substring(filename.lastIndexOf(".") + 1));
			systemService.save(document);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("失败："+e.getMessage());
		}
		return ajaxJson;
	}
	


	/**
	 * 文件添加跳转
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addFiles")
	public ModelAndView addFiles(HttpServletRequest req) {
		return new ModelAndView("system/document/files");
	}

	/**
	 * 文件编辑跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "editFiles")
	public ModelAndView editFiles(TSDocument doc, ModelMap map,HttpServletRequest request) {
		if (StringUtil.isNotEmpty(doc.getId())) {
			doc = systemService.getEntity(TSDocument.class, doc.getId());
			map.put("doc", doc);
			TSAttachment attachment = systemService.get(TSAttachment.class, doc.getId());
			map.put("attachment",attachment);
		}
		return new ModelAndView("system/document/files");
	}
	
	/**
	 * �?存文件
	 *
	 * @param document
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, TSDocument document) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		TSTypegroup tsTypegroup=systemService.getTypeGroup("fieltype","文档分类");
		TSType tsType = systemService.getType("files","附件", tsTypegroup);
		String documentId = oConvertUtils.getString(request.getParameter("documentId"));// 文件ID
		String documentTitle = oConvertUtils.getString(request.getParameter("documentTitle"));// 文件标题
		if (StringUtil.isNotEmpty(documentId)) {
			document.setId(documentId);
			document = systemService.getEntity(TSDocument.class, documentId);
			document.setDocumentTitle(documentTitle);
		}
		document.setSubclassname(MyClassLoader.getPackPath(document));
		document.setCreatedate(DateUtils.gettimestamp());
		document.setTSType(tsType);
		UploadFile uploadFile = new UploadFile(request, document);
		uploadFile.setCusPath("files");
		//设置weboffice转化�?�?设置该字段，则�?�?�在线预览转化】
		uploadFile.setSwfpath("swfpath");
		document = systemService.uploadFile(uploadFile);
		attributes.put("url", document.getRealpath());
		attributes.put("fileKey", document.getId());
		attributes.put("name", document.getAttachmenttitle());
		attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + document.getId());
		attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());
		j.setMsg("文件添加�?功");
		j.setAttributes(attributes);
		return j;
	}
	
	/**
	 * 新闻法规文件列表
	 */
	@RequestMapping(params = "documentList")
	public void documentList(TSDocument document,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDocument.class, dataGrid);
		//查询�?�件组装器

        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, document,request.getParameterMap());

		String typecode = oConvertUtils.getString(request.getParameter("typecode"));
		cq.createAlias("TSType", "TSType");
		cq.eq("TSType.typecode", typecode);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除文档
	 *
	 * @param document
	 * @return
	 */
	@RequestMapping(params = "delDocument")
	@ResponseBody
	public AjaxJson delDocument(TSDocument document, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		document = systemService.getEntity(TSDocument.class, document.getId());
		message = "" + document.getDocumentTitle() + "被删除�?功";
		systemService.delete(document);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setSuccess(true);
		j.setMsg(message);
		return j;
	}

	/**
	 * 修改文件标题
	 * @author taoYan
	 * @since 2018年8月2日
	 */
	@RequestMapping(params = "updateDoc")
	@ResponseBody
	public AjaxJson updateDoc(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			TSDocument document = systemService.getEntity(TSDocument.class,id);
			document.setDocumentTitle(title);
			systemService.updateEntitie(document);
			j.setSuccess(true);
			j.setMsg("文件标题修改�?功!");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("文件标题修改失败!");
		}
		
		return j;
	}

	
	/**
	 * �?��?列表
	 */
	@RequestMapping(params = "functionGrid")
	@ResponseBody
	public  Object functionGrid(HttpServletRequest request,TreeGrid treegrid, Integer type,HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class,dataGrid);
		boolean pageflag=true;
		String selfId = request.getParameter("selfId");
		if (selfId != null) {
			cq.notEq("id", selfId);
		}
		if (treegrid.getId() != null) {
			pageflag=false;
			cq.eq("TSFunction.id", treegrid.getId());
		}
		if (treegrid.getId() == null) {
			cq.isNull("TSFunction");
		}
		if(type != null){
			cq.eq("functionType", type.shortValue());
		}
		cq.addOrder("functionOrder", SortDirection.asc);
		cq.add();

		//--手工加载数�?��?��?�?�件--------
		//获�?�装载数�?��?��?的�?�件HQL
		cq = HqlGenerateUtil.getDataAuthorConditionHql(cq, new TSFunction());
		cq.add();

		List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq, pageflag);
		Long total=systemService.getCountForJdbc("select count(*) from t_s_function where functionlevel=0");
		//�?��?�管�?�页�?�：�?��?�排�?
		Collections.sort(functionList, new NumberComparator());
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setIcon("TSIcon_iconPath");
		treeGridModel.setTextField("functionName");
		treeGridModel.setParentText("TSFunction_functionName");
		treeGridModel.setParentId("TSFunction_id");
		treeGridModel.setSrc("functionUrl");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("TSFunctions");
		// 添加排�?字段
		treeGridModel.setOrder("functionOrder");
		//添加�?��?�图标样�?
		treeGridModel.setIconStyle("functionIconStyle");

		treeGridModel.setFunctionType("functionType");

		treeGrids = systemService.treegrid(functionList, treeGridModel);

		MutiLangUtil.setMutiTree(treeGrids);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("rows",treeGrids);
		jsonObject.put("total",total);
		if (pageflag){
			return jsonObject;
		}
		return treeGrids;

	}

	/**
	 * �?��?列表页�?�跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "function")
	public ModelAndView function(ModelMap model) {
		return new ModelAndView("com/jeecg/demo/functionList");
	}
	
	
	/**
	 * �?��?�进入�?�排�?多选界�?�
	 */
	@RequestMapping(params = "selectSort")
	public ModelAndView selectSort() {
		return new ModelAndView("com/jeecg/demo/form_selectSort");
	}
	
	/**
	 * 跳转�?�排�?多选用户选择界�?�
	 * @return
	 */
	@RequestMapping(params = "gridSelectdemo")
	public ModelAndView gridSelectdemo() {
		return new ModelAndView("com/jeecg/demo/gridSelectdemo");
	}
	
	/**
	 * �?�排�?多选界�?�查用户表放在Datagrid中
	 * @param user
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(params = "easyUIGrid", method = RequestMethod.POST)
	@ResponseBody
	public void getEasyUIGrid(TSUser user,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid)throws Exception{
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        //查询�?�件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
        Short[] userstate = new Short[]{Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden};
        cq.in("status", userstate);
        cq.eq("deleteFlag", Globals.Delete_Normal);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "ztreeDemo")
	public ModelAndView ztreeDemo(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/ztreeDemo");
	}
	
	
	@RequestMapping(params="getTreeDemoData",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson getTreeDemoData(TSDepart depatr,HttpServletResponse response,HttpServletRequest request ){
		AjaxJson j = new AjaxJson();
		try{
			List<TSDepart> depatrList = new ArrayList<TSDepart>();
			StringBuffer hql = new StringBuffer(" from TSDepart t");
			depatrList = this.systemService.findHql(hql.toString());
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = null;
			for (TSDepart tsdepart : depatrList) {
				map = new HashMap<String,Object>();
				map.put("chkDisabled",false);
				map.put("click", true);
				map.put("id", tsdepart.getId());
				map.put("name", tsdepart.getDepartname());
				map.put("nocheck", false);
				map.put("struct","TREE");
				map.put("title",tsdepart.getDepartname());
				if (tsdepart.getTSPDepart() != null) {
					map.put("parentId",tsdepart.getTSPDepart().getId());
				}else {
					map.put("parentId","0");
				}
				dataList.add(map);
			}
		j.setObj(dataList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * 删除部门
	 * @param depart
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSDepart depart, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		depart = systemService.getEntity(TSDepart.class, depart.getId());
        Long childCount = systemService.getCountForJdbcParam("select count(1) from t_s_depart where parentdepartid = ?", depart.getId());
        if(childCount>0){
        	j.setSuccess(false);
        	j.setMsg("有下级,�?能删除");
        	return j;
        }
        systemService.executeSql("delete from t_s_role_org where org_id=?", depart.getId());
        j.setMsg("删除�?功");
		return j;
	}

	/**
	 * 多选项�?�demo
	 */
	@RequestMapping(params = "tabsDemo")
	public ModelAndView tabsDemo(HttpServletRequest request) {
		logger.info("----多选项�?�demo转入页�?�-----");
		return new ModelAndView("com/jeecg/demo/tabsDemo");
	}
	@RequestMapping(params = "tabDemo")
	public ModelAndView tabDemo(HttpServletRequest request) {
		logger.info("----选项�?�demo转入页�?�-----");
		return new ModelAndView("com/jeecg/demo/tabDemo");
	}
	
	/**
	 * 常用示例Demo:接�?�测试页�?�跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "interfaceTestDemo")
	public ModelAndView interfaceTestDemo(HttpServletRequest request) {
		logger.info("----接�?�测试demo转入页�?�-----");
		return new ModelAndView("com/jeecg/demo/form_interfaceTestDemo");
	}
	
	/**
	 * 常用示例Demo:接�?�测试
	 * @param request
	 * @param response
	 * @return AjaxJson
	 */
	@RequestMapping(params = "interfaceTest")
	@ResponseBody
	public AjaxJson testInterface(HttpServletRequest request,HttpServletResponse response) {
			 AjaxJson j=new AjaxJson();
		 try {
			 String serverUrl = request.getParameter("serverUrl");//请求的地�?�
			 String requestBody = request.getParameter("requestBody");//请求的�?�数
			 String requestMethod = request.getParameter("requestMethod");//请求的方�?
				 if(requestMethod.equals("POST")){
					 if(requestBody !=""){
						 logger.info("----请求接�?�开始-----");
						 JSONObject sendPost = HttpRequest.sendPost(serverUrl, requestBody);
						 logger.info("----请求接�?�结�?�-----"+sendPost);
						 j.setSuccess(true);
						 j.setObj(sendPost.toJSONString());
					 }else{
						 j.setSuccess(false);
						 j.setObj("请填写请求�?�数");
					 }
					 
				 }
				 if(requestMethod.equals("GET")){
					  logger.info("----请求接�?�开始-----");
					  JSONObject sendGet = HttpRequest.sendGet(serverUrl, requestBody);
					  logger.info("----请求接�?�结�?�-----"+sendGet.toJSONString());
					  j.setSuccess(true);
					  j.setObj(sendGet);
				 }
		} catch (Exception e) {
			j.setSuccess(false);
			j.setObj("�?务器请求失败");
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * Webupload上传
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "webuploader")
	public ModelAndView webuploader(HttpServletRequest request) {
		logger.info("----webuploaderdemo-----");
		return new ModelAndView("com/jeecg/demo/form_webuploader");
	}
	
	/**
	 * WebUploader
	 * 文件上传处�?�
	 */
	@RequestMapping("/filedeal")
    @ResponseBody
    public AjaxJson filedeal(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        String msg="";
        String ctxPath=ResourceUtil.getConfigByName("webUploadpath");//demo中设置为D://upFiles,实际项目应因事制宜
        try {
        	String fileName = null;
        	String bizType=request.getParameter("bizType");//上传业务�??称
        	String bizPath=StoreUploadFilePathEnum.getPath(bizType);//根�?�业务�??称判断上传路径
        	String nowday=new SimpleDateFormat("yyyyMMdd").format(new Date());
    		File file = new File(ctxPath+"/"+bizPath+"/"+nowday);
    		if (!file.exists()) {
    			file.mkdirs();// 创建文件根目录
    		}
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile mf=multipartRequest.getFile("file");// 获�?�上传文件对象
            String orgName = mf.getOriginalFilename();// 获�?�文件�??
    		fileName = String.valueOf(UUID.randomUUID().getMostSignificantBits()).replace("-", "")+ orgName.substring(orgName.lastIndexOf("."));
    		String savePath = file.getPath() + "/" + fileName;
    		File savefile = new File(savePath);
    		FileCopyUtils.copy(mf.getBytes(), savefile);
			msg="上传�?功";
			j.setMsg(msg);
			String dbpath=bizPath+"/"+nowday+"/"+fileName;
			Map<String,Object> info = new HashMap<String,Object>();
			info.put("filename", orgName.substring(0,orgName.lastIndexOf(".")));
			info.put("filesize", mf.getSize());
			info.put("filetype", orgName.substring(orgName.lastIndexOf(".")));
			info.put("filepath", dbpath);
			j.setAttributes(info);
        } catch (IOException e) {
			j.setSuccess(false);
			logger.info(e.getMessage());
		}
		j.setMsg(msg);
        return j;
    }
	/**
	 * 删除处�?�
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/filedelete")
    @ResponseBody
    public AjaxJson filedelete(HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        String msg="";
        String ctxPath=ResourceUtil.getConfigByName("webUploadpath");//demo中设置为D://upFiles,实际项目应因事制宜
        String path=request.getParameter("filepath");
    	String delpath=ctxPath+"/"+path;
    	File fileDelete = new File(delpath);
		if (!fileDelete.exists() || !fileDelete.isFile()) {
			msg="警告: " + delpath + "�?存在!";
			logger.info(msg);
			j.setSuccess(true);//�?存在�?�?�也给他删除
		}else{
			if(fileDelete.delete()){
				msg="--------�?功删除文件---------"+delpath;
				logger.info(msg);
			}else{
				j.setSuccess(false);
				msg="没删除�?功--jdk的问题还是你文件的问题请�?新试试";
				logger.info(msg);
			}
		}
		j.setMsg(msg);
        return j;
    }
	
	@RequestMapping("/filedown")
	public void getImgByurl(HttpServletResponse response,HttpServletRequest request) throws Exception{
		String dbpath = request.getParameter("filepath");
		if(oConvertUtils.isNotEmpty(dbpath)&&dbpath.endsWith(",")){
			dbpath = dbpath.substring(0, dbpath.length()-1);
		}
		response.setContentType("application/x-msdownload;charset=utf-8");
		String fileType = dbpath.substring(dbpath.lastIndexOf("."));
		String fileName=request.getParameter("filename")+fileType;
		String userAgent = request.getHeader("user-agent").toLowerCase();
		if (userAgent.contains("msie") || userAgent.contains("like gecko") ) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}else {  
			fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");  
		} 
		response.setHeader("Content-disposition", "attachment; filename="+ fileName);
	
		InputStream inputStream = null;
		OutputStream outputStream=null;
		try {
			String localPath=ResourceUtil.getConfigByName("webUploadpath");
			String imgurl = localPath+"/"+dbpath;
			inputStream = new BufferedInputStream(new FileInputStream(imgurl));
			outputStream = response.getOutputStream();
			byte[] buf = new byte[1024];
	        int len;
	        while ((len = inputStream.read(buf)) > 0) {
	            outputStream.write(buf, 0, len);
	        }
	        response.flushBuffer();
		} catch (Exception e) {
			logger.info("--通过�?的方�?获�?�文件异常--"+e.getMessage());
		}finally{
			if(inputStream!=null){
				inputStream.close();
			}
			if(outputStream!=null){
				outputStream.close();
			}
		}
	}
	
	@RequestMapping(params = "dropDownDatagrid",method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView dropDownDatagrid(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/dropDownDatagrid");
	}

	/**
	 * bootstrap树形列表页�?�跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "bootstrapTreeListDemo")
	public ModelAndView bootstrapTreeListDemo(ModelMap model) {
		return new ModelAndView("com/jeecg/demo/bootstrapTreeList");
	}
	/**
	 * bootstrap树形列表获�?�数�?�
	 * 
	 * @return
	 */
	@RequestMapping(params = "bootstrapDemoDatagrid",method ={RequestMethod.GET, RequestMethod.POST})
	public  void bootstrapDemoDatagrid(HttpServletRequest request,HttpServletResponse response) {
		    try {
		      String text1="[{\"id\":1,\"pid\":0,\"status\":1,\"name\":\"系统管�?�\",\"permissionValue\":\"系统\"},{\"id\":2,\"pid\":0,\"status\":1,\"name\":\"字典管�?�\",\"permissionValue\":\"字典\"},{\"id\":20,\"pid\":1,\"status\":1,\"name\":\"新增系统\",\"permissionValue\":\"新增\"},{\"id\":21,\"pid\":1,\"status\":1,\"name\":\"编辑系统\",\"permissionValue\":\"编辑\"},{\"id\":22,\"pid\":1,\"status\":1,\"name\":\"删除系统\",\"permissionValue\":\"删除\"},{\"id\":33,\"pid\":2,\"status\":1,\"name\":\"系统环境\",\"permissionValue\":\"环境\"},{\"id\":333,\"pid\":33,\"status\":1,\"name\":\"新增环境\",\"permissionValue\":\"新增\"},{\"id\":3333,\"pid\":33,\"status\":1,\"name\":\"编辑环境\",\"permissionValue\":\"编辑\"},{\"id\":233332,\"pid\":33,\"status\":0,\"name\":\"删除环境\",\"permissionValue\":\"删除\"}]";
			  response.getWriter().println(text1);
			 } catch (IOException e) {
				e.printStackTrace();
		  }
     }

	@RequestMapping(params = "plupload1")
	public ModelAndView plupload(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/plupload/plupload1");
	}
	@RequestMapping(params = "plupload2")
	public ModelAndView plupload3(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/plupload/plupload3");
	}
	@RequestMapping(params = "goPlupload")
	public ModelAndView goPlupload(HttpServletRequest request) {
		request.setAttribute("chunk", request.getParameter("chunk"));
		return new ModelAndView("com/jeecg/demo/plupload/plupload5");
	}
	/**
	 * 文件分割 请求处�?�
	 * 尚存bug List<FileItem> items = upload.parseRequest(request); 因为mvc已有已文件处�?��?置，此处获�?��?到值了
	 * �?�期�?�修改
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/doupload")
    public void doupload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ctxPath=ResourceUtil.getConfigByName("webUploadpath");//demo中设置为D://upFiles,实际项目应因事制宜
        String tempFileDir = ctxPath+File.separator+"temp";
        response.setCharacterEncoding("UTF-8");
		Integer schunk = null;//分割�?�数
		Integer schunks = null;//总分割数
		String name = null;//文件�??
		BufferedOutputStream outputStream=null; 
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				String bizType=request.getParameter("bizType");//上传业务�??称
	        	String bizPath=StoreUploadFilePathEnum.getPath(bizType);//根�?�业务�??称判断上传路径
	        	String nowday=new SimpleDateFormat("yyyyMMdd").format(new Date());
	        	String fileDir = ctxPath+File.separator+bizPath+File.separator+nowday;
	        	File file = new File(fileDir);
	    		if (!file.exists()) {
	    			file.mkdirs();// 创建文件根目录
	    		}
	    		File tempFile = new File(tempFileDir);
	    		if (!tempFile.exists()) {
	    			tempFile.mkdirs();// 创建文件临时目录
	    		}
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024);
				factory.setRepository(tempFile);//设置临时目录
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				upload.setSizeMax(5*1024*1024);//设置附近大�?？？
				List<FileItem> items = upload.parseRequest(request);
				//生�?新的文件�??
				String newFilename = null;
				for(FileItem item : items){
					if(!item.isFormField()){
						//如果是文件类型
						name = item.getName();//获�?�文件�??
						System.out.println("name:"+name);
						newFilename = UUID.randomUUID().toString().replace("-","").concat(".").concat(FilenameUtils.getExtension(name));
						System.out.println("newFilename:"+newFilename);
						if(name!=null){
							String nFname = newFilename;
							if(schunk!=null){
								nFname = schunk+"_"+name;
							}
				    		File savedFile = new File(fileDir, nFname);
							item.write(savedFile);
						}
					}else{
						//判断是�?�带分割信�?�
						if(item.getFieldName().equals("chunk")){
							schunk = Integer.parseInt(item.getString());
						}
						if(item.getFieldName().equals("chunks")){
							schunks = Integer.parseInt(item.getString());
						}
					}
				}
				System.out.println("chunk:"+schunk+"-"+schunks);
				if(schunk!=null && schunk+1 == schunks){
					outputStream = new BufferedOutputStream(new FileOutputStream(new File(fileDir,newFilename)));
					for(int i=0;i<schunks;i++){
						File itempFile = new File(fileDir,i+"_"+name);
						byte[] bytes = FileUtils.readFileToByteArray(itempFile);
						outputStream.write(bytes);
						outputStream.flush();
						itempFile.delete();
					}
					outputStream.flush();
				}
				response.getWriter().write("{\"status\":true,\"newName\":\""+newFilename+"\"}");
			} catch (FileUploadException e) {
				e.printStackTrace();
				response.getWriter().write("{\"status\":false}");
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("{\"status\":false}");
			}finally{  
	            try {  
	            	if(outputStream!=null)
	            		outputStream.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }   
		}
    }

	/**
	 *打�?�demo页�?�跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "printingDemo")
	public ModelAndView printingDemo(ModelMap model) {
		return new ModelAndView("com/jeecg/demo/printingDemo");
	}

	
	/**
	 * 多选下拉框DEMO
	 * @author taoYan
	 * @since 2018年9月5日
	 */
	@RequestMapping(params = "multiSelect")
	public ModelAndView multiSelect() {
		return new ModelAndView("com/jeecg/demo/select_multi");
	}
}
