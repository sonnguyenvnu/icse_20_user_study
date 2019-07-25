package org.jeecgframework.web.cgform.controller.cgformftl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.FormUtil;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.common.OfficeHtmlUtil;
import org.jeecgframework.web.cgform.engine.TempletContext;
import org.jeecgframework.web.cgform.entity.cgformftl.CgformFtlEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.service.cgformftl.CgformFtlServiceI;
import org.jeecgframework.web.cgform.util.TemplateUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: Controller
 * @Description: 上传Word转�?�为freemarker表�?�
 * @author 段其录
 * @date 2013-07-03 17:42:05
 * @version V1.0
 * 
 */
//@Scope("prototype")
@Controller
@RequestMapping("/cgformFtlController")
public class CgformFtlController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CgformFtlController.class);

	@Autowired
	private CgformFtlServiceI cgformFtlService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TempletContext templetContext;


	/**
	 * 模�?�展示
	 * 
	 * @author 安超
	 * @return
	 */
	@RequestMapping(params = "formEkeditor")
	public ModelAndView ckeditor(HttpServletRequest request, String id) {
		CgformFtlEntity t = systemService.get(CgformFtlEntity.class, id);
		request.setAttribute("cgformFtlEntity", t);
		if (t.getFtlContent() == null) {
			request.setAttribute("contents", "");
		} else {
			request.setAttribute("contents", new String(t.getFtlContent()));
		}
		//------longjb 20150513----for：修改增加新的编辑器ueditor页�?�------
		if(request.getParameter("editorType")==null){
			return new ModelAndView("jeecg/cgform/cgformftl/ckeditor");
		}else{
			return new ModelAndView("jeecg/cgform/cgformftl/"+request.getParameter("editorType"));
		}
	}

	/**
	 * 模�?�编辑�?存
	 * 
	 * @author 安超
	 * @return
	 */
	@RequestMapping(params = "saveFormEkeditor")
	@ResponseBody
	public AjaxJson saveCkeditor(HttpServletRequest request,
			CgformFtlEntity cgformFtlEntity, String contents) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(cgformFtlEntity.getId())) {
			CgformFtlEntity t = systemService.get(CgformFtlEntity.class,
					cgformFtlEntity.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(cgformFtlEntity, t);
				t.setFtlContent(contents);
				systemService.saveOrUpdate(t);
				j.setSuccess(true);
				j.setMsg("更新�?功");
				logger.info("["+IpUtil.getIpAddr(request)+"][online表�?�模�?�创建]更新�?功");
			} catch (Exception e) {
				e.printStackTrace();
				j.setSuccess(false);
				j.setMsg("更新失败");
			}
		}
		return j;
	}

	/**
	 * Word转Ftl列表 页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "cgformFtl")
	public ModelAndView cgformFtl(HttpServletRequest request) {
		String formid = request.getParameter("formid");
		request.setAttribute("formid", formid);
		return new ModelAndView("jeecg/cgform/cgformftl/cgformFtlList");
	}

	/**
	 * easyui AJAX请求数�?�
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagrid")
	public void datagrid(CgformFtlEntity cgformFtl, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CgformFtlEntity.class, dataGrid);
		// 查询�?�件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				cgformFtl, request.getParameterMap());
		this.cgformFtlService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Word转Ftl
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(CgformFtlEntity cgformFtl, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		cgformFtl = systemService.getEntity(CgformFtlEntity.class,
				cgformFtl.getId());
		message = "删除�?功";
		cgformFtlService.delete(cgformFtl);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		logger.info("["+IpUtil.getIpAddr(request)+"][online表�?�模�?�删除]"+message);
		j.setMsg(message);
		return j;
	}

	/**
	 * 激活Ftl
	 * 
	 * @return
	 */
	@RequestMapping(params = "active")
	@ResponseBody
	public AjaxJson active(CgformFtlEntity cgformFtl, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		try {
			// 判断有没有激活过的模�?�
			cgformFtl = systemService.getEntity(CgformFtlEntity.class,cgformFtl.getId());
			if (!cgformFtlService.hasActive(cgformFtl.getCgformId())) {
				cgformFtl.setFtlStatus("1");
				cgformFtlService.saveOrUpdate(cgformFtl);
				message = "激活�?功";
				CgFormHeadEntity po = systemService.getEntity(CgFormHeadEntity.class, cgformFtl.getCgformId());
				templetContext.removeTemplateFromCache(po.getTableName()+"_"+TemplateUtil.TemplateType.ADD.getName());
				templetContext.removeTemplateFromCache(po.getTableName()+"_"+TemplateUtil.TemplateType.DETAIL.getName());
				templetContext.removeTemplateFromCache(po.getTableName()+"_"+TemplateUtil.TemplateType.UPDATE.getName());
				systemService.addLog(message, Globals.Log_Type_UPDATE,Globals.Log_Leavel_INFO);
				logger.info("["+IpUtil.getIpAddr(request)+"][online表�?�模�?�激活]"+message+"表�??："+po.getTableName());
				j.setSuccess(true);
				j.setMsg(message);
			} else {
				message = "已有激活模�?�，请�?�消�?��?进行激活";
				j.setSuccess(true);
				j.setMsg(message);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			message = "激活失败";
			j.setSuccess(false);
			j.setMsg(message);
		}
		return j;
	}

	/**
	 * �?�消激活Ftl
	 * 
	 * @return
	 */
	@RequestMapping(params = "cancleActive")
	@ResponseBody
	public AjaxJson cancleActive(CgformFtlEntity cgformFtl,
			HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		try {
			cgformFtl = systemService.getEntity(CgformFtlEntity.class,cgformFtl.getId());
			CgFormHeadEntity po = systemService.getEntity(CgFormHeadEntity.class, cgformFtl.getCgformId());
			templetContext.removeTemplateFromCache(po.getTableName()+"_"+TemplateUtil.TemplateType.ADD.getName());
			templetContext.removeTemplateFromCache(po.getTableName()+"_"+TemplateUtil.TemplateType.DETAIL.getName());
			templetContext.removeTemplateFromCache(po.getTableName()+"_"+TemplateUtil.TemplateType.UPDATE.getName());
			cgformFtl.setFtlStatus("0");
			cgformFtlService.saveOrUpdate(cgformFtl);
			message = "�?�消激活�?功";
			logger.info("["+IpUtil.getIpAddr(request)+"][online表�?�模�?��?�消激活]"+message+"表�??："+po.getTableName());
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
			j.setSuccess(true);
			j.setMsg(message);
		} catch (Exception e) {
			logger.info(e.getMessage());
			message = "�?�消激活失败";
			j.setSuccess(false);
			j.setMsg(message);
		}
		return j;
	}

	/**
	 * 添加Word转Ftl
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(CgformFtlEntity cgformFtl, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(cgformFtl.getId())) {
			message = "更新�?功";
			CgformFtlEntity t = cgformFtlService.get(CgformFtlEntity.class,
					cgformFtl.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(cgformFtl, t);
				cgformFtlService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加�?功";
			cgformFtlService.save(cgformFtl);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}

		return j;
	}

	/**
	 * 转Ftl列表页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(CgformFtlEntity cgformFtl,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cgformFtl.getId())) {
			cgformFtl = cgformFtlService.getEntity(CgformFtlEntity.class,
					cgformFtl.getId());
		}
		HttpSession session = ContextHolderUtils.getSession();
		String lang = (String)session.getAttribute("lang");
		StringBuffer sb = new StringBuffer();

		sb.append("<!DOCTYPE html xmlns:m=\"http://schemas.microsoft.com/office/2004/12/omml\"><head><title></title>");

		sb.append("<base href=\"${basePath}/\" />");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/jquery/jquery-1.8.3.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/jquery-plugs/i18n/jquery.i18n.properties.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/tools/dataformat.js\"></script>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"${basePath}/plug-in/accordion/css/accordion.css\"></link>");
    	sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"${basePath}/plug-in/easyui/themes/default/easyui.css\" type=\"text/css\"></link>");
    	sb.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/easyui/locale/zh-cn.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/tools/syUtil.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/My97DatePicker/WdatePicker.js\"></script>");
    	sb.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/tools/css/metrole/common.css\" type=\"text/css\"></link>");
    	sb.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/ace/css/font-awesome.css\" type=\"text/css\"></link>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/lhgDialog/lhgdialog.min.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/layer/layer.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/tools/curdtools.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/tools/easyuiextend.js\"></script>");
    	sb.append("<link id=\"easyuiTheme\" rel=\"stylesheet\" href=\"${basePath}/plug-in/easyui/themes/metrole/main.css\" type=\"text/css\"></link>");
    	sb.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/uploadify/css/uploadify.css\" type=\"text/css\"></link>");

    	//sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/uploadify/jquery.uploadify-3.1.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/plupload/plupload.full.min.js\"></script>");

    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/tools/Map.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/Validform/js/datatype_zh-cn.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></script>");
    	sb.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/Validform/css/metrole/style.css\" type=\"text/css\"/>");
    	sb.append("<link rel=\"stylesheet\" href=\"${basePath}/plug-in/Validform/css/metrole/tablefrom.css\" type=\"text/css\"/>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/ueditor/ueditor.config.js\"></script>");
    	sb.append("<script type=\"text/javascript\" src=\"${basePath}/plug-in/ueditor/ueditor.all.js\"></script>");
    	sb.append("<style type=\"text/css\">body{font-size:12px;}table{border: 1px solid #000000;padding:0; ");
		sb.append("margin:0 auto;border-collapse: collapse;width:100%;align:right;}td {border: 1px solid ");
		sb.append("#000000;background: #fff;font-size:12px;padding: 3px 3px 3px 8px;color: #000000;word-break: keep-all;}");

		sb.append(".ui_state_highlight{border:1px solid #39befb;position:relative;display:inline-block;cursor:pointer;text-align:center;overflow:hidden;border-radius:10px;padding:4px 30px;font-size:14px;background-color:#39befb;color:#fff}.ui_state_highlight:hover{background-color:#3aace5;color:#000;}");

		sb.append("</style></head>"); 
		
		sb.append("<body>");
		
		sb.append("<div align=\"center\" id=\"sub_tr\" style=\"display: none;\"><input class=\"ui_state_highlight\" onclick=\"neibuClick()\" type=\"button\" value=\"�??交\" /></div>");
		sb.append("</body>");

		sb.append("<script type=\"text/javascript\">$(function(){$(\"#formobj\").Validform({tiptype:1,btnSubmit:\"#btn_sub\",btnReset:\"#btn_reset\",ajaxPost:true,usePlugin:{passwordstrength:{minLen:6,maxLen:18,trigger:function(obj,error){if(error){obj.parent().next().find(\".Validform_checktip\").show();obj.find(\".passwordStrength\").hide();}else{$(\".passwordStrength\").show();obj.parent().next().find(\".Validform_checktip\").hide();}}}},callback:function(data){if(data.success==true){uploadFile(data);}else{if(data.responseText==''||data.responseText==undefined){$.messager.alert('错误', data.msg);$.Hidemsg();}else{try{var emsg = data.responseText.substring(data.responseText.indexOf('错误�??述'),data.responseText.indexOf('错误信�?�')); $.messager.alert('错误',emsg);$.Hidemsg();}catch(ex){$.messager.alert('错误',data.responseText+'');}} return false;}if(!neibuClickFlag){var win = frameElement.api.opener; win.reloadTable();}}});});</script>");

		sb.append("<script type=\"text/javascript\">");
		sb.append("$(function(){if(location.href.indexOf(\"goDetail.do\")!=-1){$(\".jeecgDetail\").hide();}if(location.href.indexOf(\"goDetail.do\")!=-1){$(\"#formobj\").find(\":input\").attr(\"disabled\",\"disabled\");}if(location.href.indexOf(\"goAddButton.do\")!=-1||location.href.indexOf(\"goUpdateButton.do\")!=-1){$(\"#sub_tr\").show();}});");
		sb.append(" var neibuClickFlag = false;");
		sb.append(" function neibuClick() {neibuClickFlag = true;$('#btn_sub').trigger('click');}");
		sb.append(" function uploadFile(data){if(!$(\"input[name='id']\").val()){if(data.obj!=null && data.obj!='undefined'){$(\"input[name='id']\").val(data.obj.id);}} if($(\".uploadify-queue-item\").length>0){upload();}else{if (neibuClickFlag){alert(data.msg);neibuClickFlag = false;}else {var win = frameElement.api.opener;win.reloadTable();win.tip(data.msg);frameElement.api.close();}}}");
		sb.append(" $.dialog.setting.zIndex =9999;");
		sb.append(" function del(url,obj){$.dialog.confirm(\"确认删除该�?�记录?\", function(){$.ajax({async : false,cache : false,type : 'POST',url : url,error : function() {},success : function(data) {var d = $.parseJSON(data);if (d.success) {var msg = d.msg;tip(msg);$(obj).closest(\"tr\").hide(\"slow\");}}});}, function(){ });}");
		sb.append("</script>");

		sb.append("<script type=\"text/javascript\">${js_plug_in?if_exists}</script></html>");
		
		
		req.setAttribute("cgformStr", sb);
		req.setAttribute("cgformFtlPage", cgformFtl);
		if("02".equals(cgformFtl.getEditorType())){
			return new ModelAndView("jeecg/cgform/cgformftl/cgformFtlUEditor");
		}else if("03".equals(cgformFtl.getEditorType())){
			return new ModelAndView("jeecg/cgform/cgformftl/cgformFtl");
		}else{
			return new ModelAndView("jeecg/cgform/cgformftl/cgformFtlEditor");
		}
	}

	/**
	 * �?存转�?�表�?�文件
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveWordFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveWordFiles(HttpServletRequest request,
			HttpServletResponse response, CgformFtlEntity cgformFtl) {
		String message = null;
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();

		LogUtil.info("-------------------------step.1-------------------------------------");
		String fileKey = oConvertUtils.getString(request.getParameter("id"));// 文件ID
		String cgformId = oConvertUtils.getString(request
				.getParameter("cgformId"));// formid
		String cgformName = oConvertUtils.getString(request
				.getParameter("cgformName"));// formname
		String ftlStatus = oConvertUtils.getString(request
				.getParameter("ftlStatus"));// formStatus
		if (oConvertUtils.isEmpty(ftlStatus)) {
			ftlStatus = "0";
		}

		if (StringUtil.isNotEmpty(fileKey)) {
			cgformFtl.setId(fileKey);
			cgformFtl = systemService.getEntity(CgformFtlEntity.class, fileKey);
		} else {
			cgformFtl.setFtlVersion(cgformFtlService.getNextVarsion(cgformId));
		}
		LogUtil.info("-------------------------step.2-------------------------------------");
		cgformFtl.setCgformId(cgformId);
		cgformFtl.setCgformName(cgformName);
		cgformFtl.setFtlStatus(ftlStatus);

		UploadFile uploadFile = new UploadFile(request, cgformFtl);
		uploadFile.setCusPath("forms");
		message = null;
		try {

			uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
			MultipartHttpServletRequest multipartRequest = uploadFile
					.getMultipartRequest();

			String uploadbasepath = uploadFile.getBasePath();// 文件上传根目录
			if (uploadbasepath == null) {
				uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
			}
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			// 文件数�?�库�?存路径
			String path = uploadbasepath + File.separator;// 文件�?存在硬盘的相对路径
			String realPath = uploadFile.getMultipartRequest().getSession()
					.getServletContext().getRealPath(File.separator)
					+ path;// 文件的硬盘真实路径
			File file = new File(realPath);
			if (!file.exists()) {
				file.mkdir();// 创建根目录
			}
			if (uploadFile.getCusPath() != null) {
				realPath += uploadFile.getCusPath() + File.separator;
				path += uploadFile.getCusPath() +File.separator;
				file = new File(realPath);
				if (!file.exists()) {
					file.mkdir();// 创建文件自定义�?目录
				}
			} else {
				realPath += DateUtils.getDataString(DateUtils.yyyyMMdd) +File.separator;
				path += DateUtils.getDataString(DateUtils.yyyyMMdd) +File.separator;
				file = new File(realPath);
				if (!file.exists()) {
					file.mkdir();// 创建文件时间�?目录
				}
			}
			LogUtil.info("-------------------------step.3-------------------------------------");
			String fileName = "";
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				MultipartFile mf = entity.getValue();// 获�?�上传文件对象
				fileName = mf.getOriginalFilename();// 获�?�文件�??
				String extend = FileUtils.getExtend(fileName);// 获�?�文件扩展�??
				String myfilename = "";
				String myhtmlfilename = "";
				String noextfilename = "";// �?带扩展�??
				if (uploadFile.isRename()) {

					noextfilename = DateUtils
							.getDataString(DateUtils.yyyymmddhhmmss)
							+ StringUtil.random(8);// 自定义文件�??称
					myfilename = noextfilename + "." + extend;// 自定义文件�??称
				} else {
					myfilename = fileName;
				}

				String savePath = realPath + myfilename;// 文件�?存全路径
				cgformFtl.setFtlWordUrl(fileName);
				File savefile = new File(savePath);
				FileCopyUtils.copy(mf.getBytes(), savefile);

				myhtmlfilename = realPath + noextfilename + ".html";
				String myftlfilename = realPath + noextfilename + ".ftl";
				LogUtil.info("-------------------------step.4-------------------------------------");
				// 开始转�?�表�?�文件
				OfficeHtmlUtil officeHtml = new OfficeHtmlUtil();

				// 方�?一：jacob.jar方�?word转html
				officeHtml.wordToHtml(savePath, myhtmlfilename);
				String htmlStr = officeHtml.getInfo(myhtmlfilename);
				htmlStr = officeHtml.doHtml(htmlStr);

				// 方�?二：poi方�?word转html
				// officeHtml.WordConverterHtml(savePath, myhtmlfilename);
				// String htmlStr = officeHtml.getInfo(myhtmlfilename);
				// htmlStr = officeHtml.doPoiHtml(htmlStr);

				officeHtml.stringToFile(htmlStr, myftlfilename);
				// js plugin start
				StringBuilder script = new StringBuilder("");
				script.append("<div align=\"center\" id=\"sub_tr\" style=\"display: none;\"><input class=\"ui_state_highlight\" onclick=\"neibuClick()\" type=\"button\" value=\"�??交\" /></div>");
				script.append("</body>");

				script.append("<script type=\"text/javascript\">$(function(){$(\"#formobj\").Validform({tiptype:function(msg,o,cssctl){if(o.type == 3){layer.open({title:'�??示信�?�',content:msg,icon:5,shift:6,btn:false,shade:false,time:5000,cancel:function(index){o.obj.focus();layer.close(index);},yes:function(index){o.obj.focus();layer.close(index);},})}},btnSubmit:\"#btn_sub\",btnReset:\"#btn_reset\",ajaxPost:true,usePlugin:{passwordstrength:{minLen:6,maxLen:18,trigger:function(obj,error){if(error){obj.parent().next().find(\".Validform_checktip\").show();obj.find(\".passwordStrength\").hide();}else{$(\".passwordStrength\").show();obj.parent().next().find(\".Validform_checktip\").hide();}}}},callback:function(data){if(data.success==true){uploadFile(data);}else{if(data.responseText==''||data.responseText==undefined){$.messager.alert('错误', data.msg);$.Hidemsg();}else{try{var emsg = data.responseText.substring(data.responseText.indexOf('错误�??述'),data.responseText.indexOf('错误信�?�')); $.messager.alert('错误',emsg);$.Hidemsg();}catch(ex){$.messager.alert('错误',data.responseText+'');}} return false;}if(!neibuClickFlag){var win = frameElement.api.opener; win.reloadTable();}}});});</script>");
				script.append("<script type=\"text/javascript\">");
				script.append("$(function(){if(location.href.indexOf(\"goDetail.do\")!=-1){$(\".jeecgDetail\").hide();}if(location.href.indexOf(\"goDetail.do\")!=-1){$(\"#formobj\").find(\":input\").attr(\"disabled\",\"disabled\");}if(location.href.indexOf(\"goAddButton.do\")!=-1||location.href.indexOf(\"goUpdateButton.do\")!=-1){$(\"#sub_tr\").show();}});");
				script.append(" var neibuClickFlag = false;");
				script.append(" function neibuClick() {neibuClickFlag = true;$('#btn_sub').trigger('click');}");
				script.append(" function uploadFile(data){if(!$(\"input[name='id']\").val()){if(data.obj!=null && data.obj!='undefined'){$(\"input[name='id']\").val(data.obj.id);}} if($(\".uploadify-queue-item\").length>0){upload();}else{if (neibuClickFlag){alert(data.msg);neibuClickFlag = false;}else {var win = frameElement.api.opener;win.reloadTable();win.tip(data.msg);frameElement.api.close();}}}");
				script.append(" $.dialog.setting.zIndex =9999;");
				script.append(" function del(url,obj){$.dialog.confirm(\"确认删除该�?�记录?\", function(){$.ajax({async : false,cache : false,type : 'POST',url : url,error : function() {},success : function(data) {var d = $.parseJSON(data);if (d.success) {var msg = d.msg;tip(msg);$(obj).closest(\"tr\").hide(\"slow\");}}});}, function(){ });}");
				script.append("</script>");

				script.append("<script type=\"text/javascript\">");
				script.append("${js_plug_in?if_exists}");
				script.append("</script>");
				htmlStr = htmlStr.replace("</html>", script.toString()
						+ "</html>");
				// js plugin end
				cgformFtl.setFtlContent(htmlStr);
				cgformFtlService.saveOrUpdate(cgformFtl);
				LogUtil.info("-------------------------step.5-------------------------------------");
			}
			logger.info("["+IpUtil.getIpAddr(request)+"][online表�?�模�?�word上传]"+message+"表�??："+cgformName);
		} catch (Exception e1) {
			LogUtil.error(e1.toString());
			message = e1.toString();
		}

		attributes.put("id", cgformFtl.getId());
		if (StringUtil.isNotEmpty(message))
			j.setMsg("Word 模�?�上传失败," + message);
		else
			j.setMsg("Word 模�?�上传�?功");
		j.setAttributes(attributes);

		return j;
	}
	// for：放弃jacob和poi上传word，改用ckeditor
	@RequestMapping(params = "cgformFtl2")
	public ModelAndView cgformFtl2(HttpServletRequest request) {

		String formid = request.getParameter("formid");
		CgFormHeadEntity po = systemService.getEntity(CgFormHeadEntity.class, formid);
		request.setAttribute("formid", formid);
		request.setAttribute("tableName", po.getTableName());

		return new ModelAndView("jeecg/cgform/cgformftl/cgformFtlList2");
	}

	@RequestMapping(params = "saveEditor")
	@ResponseBody
	public AjaxJson saveEditor(CgformFtlEntity cgformFtl,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String fileKey = oConvertUtils.getString(request.getParameter("id"));// 文件ID
		String cgformId = oConvertUtils.getString(request
				.getParameter("cgformId"));
		String cgformName = oConvertUtils.getString(request
				.getParameter("cgformName"));
		String ftlStatus = oConvertUtils.getString(request
				.getParameter("ftlStatus"));
		String ftlVersion = oConvertUtils.getString(request
				.getParameter("ftlVersion"));
		String ftlWordUrl = oConvertUtils.getString(request
				.getParameter("ftlWordUrl"));
		String createBy = oConvertUtils.getString(request
				.getParameter("createBy"));
		String createName = oConvertUtils.getString(request
				.getParameter("createName"));
		String createDate = oConvertUtils.getString(request
				.getParameter("createDate"));
		if (oConvertUtils.isEmpty(ftlStatus))
			ftlStatus = "0";

		cgformFtl.setCgformId(cgformId);
		cgformFtl.setCgformName(cgformName);
		cgformFtl.setFtlStatus(ftlStatus);
		if (StringUtil.isNotEmpty(fileKey)) {
			cgformFtl.setId(fileKey);
			if(StringUtil.isNotEmpty(ftlVersion))
				cgformFtl.setFtlVersion(Integer.valueOf(ftlVersion));
			if (StringUtil.isNotEmpty(ftlWordUrl))
				cgformFtl.setFtlWordUrl(ftlWordUrl);
			if (StringUtil.isNotEmpty(createBy))
				cgformFtl.setCreateBy(createBy);
			if (StringUtil.isNotEmpty(createName))
				cgformFtl.setCreateName(createName);
			if (StringUtil.isNotEmpty(createDate))
				cgformFtl.setCreateDate(DateUtils.str2Date(createDate, DateUtils.date_sdf));

			if (cgformFtl.getFtlContent()!=null&&cgformFtl.getFtlContent().indexOf("<form")<0){
				//!"<form".equalsIgnoreCase(cgformFtl.getFtlContent())) {
				String ls_form = "<form action=\"cgFormBuildController.do?saveOrUpdate\" id=\"formobj\" name=\"formobj\" method=\"post\">"
						+ "<input type=\"hidden\" name=\"tableName\" value=\"${tableName?if_exists?html}\" />"
						+ "<input type=\"hidden\" name=\"id\" value=\"${id?if_exists?html}\" />"
						+ "<input type=\"hidden\" id=\"btn_sub\" class=\"btn_sub\" />#{jform_hidden_field}<table";
				cgformFtl.setFtlContent(cgformFtl.getFtlContent().replace(
						"<table", ls_form));
				cgformFtl.setFtlContent(cgformFtl.getFtlContent().replace(
						"</table>", "</table></form>"));
			}
			cgformFtlService.saveOrUpdate(cgformFtl);
			j.setMsg("修改�?功");
		} else {
			cgformFtl.setFtlVersion(cgformFtlService.getNextVarsion(cgformId));

			String ls_form = "<form action=\"cgFormBuildController.do?saveOrUpdate\" id=\"formobj\" name=\"formobj\" method=\"post\">"
					+ "<input type=\"hidden\" name=\"tableName\" value=\"${tableName?if_exists?html}\" />"
					+ "<input type=\"hidden\" name=\"id\" value=\"${id?if_exists?html}\" />"
					+ "<input type=\"hidden\" id=\"btn_sub\" class=\"btn_sub\" />#{jform_hidden_field}<table";
			cgformFtl.setFtlContent(cgformFtl.getFtlContent().replace("<table",
					ls_form));
			cgformFtl.setFtlContent(cgformFtl.getFtlContent().replace(
					"</table>", "</table></form>"));
			cgformFtlService.save(cgformFtl);
			j.setMsg("上传�?功");
		}
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("id", cgformFtl.getId());
		j.setAttributes(attributes);
		return j;
	}
	// for：放弃jacob和poi上传word，改用ckeditor
//----------longjb 20150602 ---for: html解�?预览
	@RequestMapping(params = "parseUeditorOld")
	@ResponseBody
	public AjaxJson parseUeditorOld(String parseForm,String action,HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		try {
			JSONObject json = new JSONObject().fromObject(parseForm);
			//System.out.println(json.getString("parse"));
			//System.out.println(json.getString("data"));
			// 判断有没有激活过的模�?�
			message = FormUtil.GetHtml(json.getString("parse"),json.getString("data"), action);
			j.setMsg(message);
			j.setSuccess(true);
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			message = "解�?异常"+e.getMessage();
			j.setSuccess(false);
			j.setMsg(message);
		}
		return j;
	}
	@RequestMapping(params = "parseUeditor")
	@ResponseBody
	public AjaxJson parseUeditor(String parseForm,String action,HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		try {
//			System.out.println(parseForm);
//			System.out.println(contentData);
//			JSONObject json = new JSONObject().fromObject(parseForm);
//			System.out.println(json.getString("parse"));
//			System.out.println(json.getString("data"));
//			// 判断有没有激活过的模�?�
//			message = FormUtil.GetHtml(json.getString("parse"),json.getString("data"), action);

			if(StringUtils.isNotBlank(parseForm)){
				TemplateUtil tool = new TemplateUtil();
				Map<String,Object> map = tool.processor(parseForm);
				j.setMsg(map.get("parseHtml").toString().replaceAll("\"", "&quot;"));
			} else {
				j.setMsg("");
			}

			j.setSuccess(true);
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			message = "解�?异常"+e.getMessage();
			j.setSuccess(false);
			j.setMsg(message);
		}
		return j;
	}

}
