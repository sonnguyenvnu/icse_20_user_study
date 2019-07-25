package org.jeecgframework.web.cgform.controller.generate;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.codegenerate.database.JeecgReadTable;
import org.jeecgframework.codegenerate.extcommon.CreateFileConfig;
import org.jeecgframework.codegenerate.extcommon.onetomany.CgformCodeOne2ManyExtCommonGenerate;
import org.jeecgframework.codegenerate.extcommon.single.CgformCodeExtCommonGenerate;
import org.jeecgframework.codegenerate.generate.CgformCodeGenerate;
import org.jeecgframework.codegenerate.generate.onetomany.CgformCodeGenerateOneToMany;
import org.jeecgframework.codegenerate.pojo.CreateFileProperty;
import org.jeecgframework.codegenerate.pojo.onetomany.CodeParamEntity;
import org.jeecgframework.codegenerate.pojo.onetomany.SubTableEntity;
import org.jeecgframework.codegenerate.util.CodeResourceUtil;
import org.jeecgframework.codegenerate.util.CodeStringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.enums.OnlineGenerateEnum;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.cgform.entity.button.CgformButtonEntity;
import org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJsEntity;
import org.jeecgframework.web.cgform.entity.generate.GenerateEntity;
import org.jeecgframework.web.cgform.entity.generate.GenerateSubListEntity;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.cgform.service.button.CgformButtonServiceI;
import org.jeecgframework.web.cgform.service.button.CgformButtonSqlServiceI;
import org.jeecgframework.web.cgform.service.cgformftl.CgformFtlServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.enhance.CgformEnhanceJsServiceI;
import org.jeecgframework.web.cgform.service.impl.generate.TempletContextWord;
import org.jeecgframework.web.cgform.util.GenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @Title:CgGenerateController
 * @description:智能表�?�代�?生�?器[根�?�智能表�?��?置+代�?生�?设置->生�?代�?]
 * @author 赵俊夫
 * @date Sep 7, 2013 12:19:32 PM
 * @version V1.0
 */
@Controller
@RequestMapping("/generateController")
public class GenerateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GenerateController.class);
	
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	@Autowired
	private CgformButtonServiceI cgformButtonService;
	@Autowired
	private CgformButtonSqlServiceI cgformButtonSqlService;
	@Autowired
	private CgformEnhanceJsServiceI cgformEnhanceJsService;
	@Autowired
	private TempletContextWord templetContextWord;
	@Autowired
	private DataBaseService dataBaseService;
	@Autowired
	private CgformFtlServiceI cgformFtlService;
	/**
	 * 代�?生�?�?置页�?�
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "gogenerate")
	public ModelAndView gogenerate( CgFormHeadEntity cgFormHead,HttpServletRequest request) {
		if (StringUtil.isNotEmpty(cgFormHead.getId())) {
			cgFormHead = cgFormFieldService.getEntity(
					CgFormHeadEntity.class, cgFormHead.getId());
		}else{
			throw new RuntimeException("表�?��?置�?存在");
		}
		String returnModelAndView = null;
		Map<String,String> entityNameMap = new HashMap<String,String>(0);
		if(cgFormHead.getJformType()==1 || cgFormHead.getJformType()==3){
			//如果是�?�表或者附表，则进入�?�表模型

			request.setAttribute("jspModeList", GenerateUtil.getOnlineGenerateEnum("single","ext","Y".equals(cgFormHead.getIsTree())));// 默认�?版本模�?�(IE8+/�?支�?移动/列表标签)

			returnModelAndView = "jeecg/cgform/generate/single";
		}else{
			//如果是主表，则进入一对多模型
			List<CgFormHeadEntity> subTableList = new ArrayList<CgFormHeadEntity>();
			if(StringUtil.isNotEmpty(cgFormHead.getSubTableStr())){
				String[] subTables = cgFormHead.getSubTableStr().split(",");
				for(String subTable :subTables){
					CgFormHeadEntity subHead = cgFormFieldService.getCgFormHeadByTableName(subTable);
					subTableList.add(subHead);
					entityNameMap.put(subHead.getTableName(), JeecgReadTable.formatFieldCapital(subHead.getTableName()));
				}
			}

			request.setAttribute("jspModeList", GenerateUtil.getOnlineGenerateEnum("onetomany","ext","Y".equals(cgFormHead.getIsTree())));// 默认�?版本模�?�(IE8+/�?支�?移动/列表标签)

			request.setAttribute("subTableList", subTableList);
			returnModelAndView = "jeecg/cgform/generate/one2many";
		}
		String projectPath = CodeResourceUtil.getProject_path();
		try{
		    Cookie[] cookies=request.getCookies();
		    if(cookies!=null){
		    for(int i=0;i<cookies.length;i++){
		        if(cookies[i].getName().equals("cookie_projectPath")){
		        String value =  cookies[i].getValue();
		        if(value!=null&&!"".equals(value)){
		        	projectPath=cookies[i].getValue();
		        	projectPath= URLDecoder.decode(projectPath, "UTF-8"); 
		            }
		         }
		        request.setAttribute("projectPath",projectPath);
		    }
		    }
		}catch(Exception e){
		    e.printStackTrace();
		}
		String entityName = JeecgReadTable.formatFieldCapital(cgFormHead.getTableName());
		entityNameMap.put(cgFormHead.getTableName(), entityName);
		request.setAttribute("cgFormHeadPage", cgFormHead);
		request.setAttribute("entityNames",entityNameMap );
		return new ModelAndView(returnModelAndView);
	}
	
	
	/**
	 * 根�?�模�?�类型查询模�?�风格
	 * @return
	 */
	@RequestMapping(params = "getOnlineTempletStyle")
	@ResponseBody
	public AjaxJson getOnlineTempletStyle(String type,String version,boolean supportTree){
		AjaxJson j = new AjaxJson();
		try {

			List<OnlineGenerateEnum> list =  GenerateUtil.getOnlineGenerateEnum(type,version,supportTree);

			List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
			Map<String,String> map = null;
			for(OnlineGenerateEnum item : list) {
				map = new HashMap<String, String>();
				map.put("code", item.getCode());
				map.put("desc", item.getDesc());
				mapList.add(map);
			}
			j.setObj(mapList);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("查询失败");
			e.printStackTrace();
		}
		return j;
	}
	/**
	 * 代�?生�?执行-�?�表
	 * @param generateEntity
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "dogenerate")
	public void dogenerate(CgFormHeadEntity cgFormHead,GenerateEntity generateEntity,CreateFileProperty createFileProperty,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		//step.1 准备好智能表�?�的�?置
		if (StringUtil.isNotEmpty(cgFormHead.getId())) {
			cgFormHead = cgFormFieldService.getEntity(
					CgFormHeadEntity.class, cgFormHead.getId());
			getCgformConfig(cgFormHead, generateEntity);
		}else{
			throw new RuntimeException("表�?��?置�?存在");
		}
		AjaxJson j =  new AjaxJson();
		String tableName = generateEntity.getTableName();
		String ftlDescription = generateEntity.getFtlDescription();
		try {
			//step.2 判断表是�?�存在
			boolean tableexist = new JeecgReadTable().checkTableExist(tableName);
			if(tableexist){

				String version = request.getParameter("version");
				OnlineGenerateEnum modeEnum = OnlineGenerateEnum.toEnum(createFileProperty.getJspMode(),version);

				if(modeEnum!=null){
					if("ext".equals(modeEnum.getVersion())){
						CgformCodeGenerate generate = new CgformCodeGenerate(createFileProperty,generateEntity);
						generate.generateToFileUserDefined();
					}

					else if("ext-common".equals(modeEnum.getVersion())){
						CreateFileConfig createFileConfig = new CreateFileConfig();
						createFileConfig.setStylePath(createFileProperty.getJspMode().replace(".", File.separator));
						createFileConfig.setTemplateRootDir("src/main/resources/jeecg/ext-common-template");
						CgformCodeExtCommonGenerate g = new CgformCodeExtCommonGenerate(createFileConfig,generateEntity);
						g.generateToFile();
					}

					j.setMsg(ftlDescription+"：功能生�?�?功，请刷新项目�?�?�，�?��?�访问路径："+CodeStringUtils.getInitialSmall(generateEntity.getEntityName())+"Controller.do?list");
				}else if("system".equals(version)){
					CgformCodeGenerate generate = new CgformCodeGenerate(createFileProperty,generateEntity);
					createFileProperty.setJspMode(OnlineGenerateEnum.ONLINE_TABLE_SINGLE.getCode());
					//判断是�?�获�?�word模�?�
					Map<String,Object> cgformFtlEntity = cgformFtlService.getCgformFtlByTableName(tableName);
					if(cgformFtlEntity!=null){
						String formhtml = templetContextWord.autoFormGenerateHtml(tableName, null, null);
						generate.setCgformJspHtml(formhtml);
					}else{

						j.setMsg("该表�?�没有激活的word模�?��?能生�?");
						try {
							String projectPath = URLEncoder.encode(generateEntity.getProjectPath(), "UTF-8");
							Cookie cookie = new Cookie("cookie_projectPath",projectPath );				
							cookie.setMaxAge(60*60*24*30); //cookie �?存30天
							response.addCookie(cookie);
							response.getWriter().print(j.getJsonStr());
							response.getWriter().flush();
						} catch (IOException e) {
							e.printStackTrace();
						}finally{
							try {
								response.getWriter().close();
							} catch (Exception e2) {
							}
						}
						return;

					}
					generate.generateToFileUserDefined();
				}else{
					j.setMsg("代�?生�?器�?支�?该页�?�风格");
				}

			}else{
				j.setMsg("表["+tableName+"] 在数�?�库中，�?存在");
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
			j.setMsg(e1.getMessage());
			throw new RuntimeException(e1.getMessage());
		}
		try {
			String projectPath = URLEncoder.encode(generateEntity.getProjectPath(), "UTF-8");
			Cookie cookie = new Cookie("cookie_projectPath",projectPath );				
			cookie.setMaxAge(60*60*24*30); //cookie �?存30天
			response.addCookie(cookie);
			response.getWriter().print(j.getJsonStr());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				response.getWriter().close();
			} catch (Exception e2) {
			}
		}
	}
	
	
	
	
	/**
	 * 代�?生�?执行-一对多
	 * @param generateEntity
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "dogenerateOne2Many")
	@ResponseBody
	public void dogenerateOne2Many(CodeParamEntity codeParamEntityIn,GenerateSubListEntity subTableListEntity,String jspMode,HttpServletRequest request,HttpServletResponse response){
		AjaxJson j =  new AjaxJson();
		//step.1 设置主表
		//从�?�?�获�?�：codeParamEntityIn
		//step.2 设置�?表集�?�
		//从�?�?�获�?�：subTabParamIn,并设置外键字段
		try{
			//step.3 填充主表的所有智能表�?��?置
			String mainTable = codeParamEntityIn.getTableName();
			//主表的智能表�?��?置
			GenerateEntity mainG = new GenerateEntity();
			mainG.setProjectPath(subTableListEntity.getProjectPath());
			mainG.setPackageStyle(subTableListEntity.getPackageStyle());

			mainG.setSupportRestful(request.getParameter("supportRestful"));

			CgFormHeadEntity mCgFormHead = cgFormFieldService.getCgFormHeadByTableName(mainTable);
			getCgformConfig(mCgFormHead, mainG);
			//step.4 填充�?表的所有智能表�?��?置
			Map<String,GenerateEntity> subsG = new HashMap<String,GenerateEntity>();
			List<SubTableEntity>  subTabParamIn = subTableListEntity.getSubTabParamIn();
			for(SubTableEntity po:subTabParamIn){
				String sTableName = po.getTableName();
				CgFormHeadEntity cgSubHead = cgFormFieldService.getCgFormHeadByTableName(sTableName);
				List<CgFormFieldEntity> colums = cgSubHead.getColumns();
				String[] foreignKeys =getForeignkeys(colums);
				po.setForeignKeys(foreignKeys);
				GenerateEntity subG = new GenerateEntity();
				getCgformConfig(cgSubHead, subG);
				subG.setEntityName(po.getEntityName());
				subG.setEntityPackage(po.getEntityPackage());
				subG.setFieldRowNum(1);
				subG.setFtlDescription(po.getFtlDescription());
				subG.setForeignKeys(foreignKeys);
				subG.setTableName(po.getTableName());
				subG.setProjectPath(subTableListEntity.getProjectPath());
				subG.setPackageStyle(subTableListEntity.getPackageStyle());
				subsG.put(sTableName, subG);
			}
			codeParamEntityIn.setSubTabParam(subTabParamIn);

			String version = request.getParameter("version");
			OnlineGenerateEnum modeEnum = OnlineGenerateEnum.toEnum(jspMode,version);

			if(modeEnum!=null){
				if("system".equals(modeEnum.getVersion())){
					//step.5 一对多(父�?表)数�?�模型,代�?生�?

					if("06".equals(jspMode)){
						CgformCodeGenerateOneToMany.oneToManyCreateBootstap(subTabParamIn, codeParamEntityIn,mainG,subsG);
					}else{
						CgformCodeGenerateOneToMany.oneToManyCreate(subTabParamIn, codeParamEntityIn,mainG,subsG);
					}

					//j.setMsg("�?功生�?增删改查->功能："+codeParamEntityIn.getFtlDescription());
				}else if("ext".equals(modeEnum.getVersion())){
					CgformCodeGenerateOneToMany.oneToManyCreateUserDefined(jspMode,subTabParamIn, codeParamEntityIn,mainG,subsG);
				}

				else if("ext-common".equals(modeEnum.getVersion())){
					CreateFileConfig createFileConfig = new CreateFileConfig();
					createFileConfig.setStylePath(jspMode.replace(".", File.separator));
					createFileConfig.setTemplateRootDir("src/main/resources/jeecg/ext-common-template");
					CgformCodeOne2ManyExtCommonGenerate g = new CgformCodeOne2ManyExtCommonGenerate(createFileConfig,subTabParamIn, codeParamEntityIn,mainG,subsG);
					g.generateToFile();
				}

				j.setMsg(codeParamEntityIn.getFtlDescription()+"：功能生�?�?功，请刷新项目�?�?�，�?��?�访问路径："+CodeStringUtils.getInitialSmall(codeParamEntityIn.getEntityName())+"Controller.do?list");
			}else{
				j.setMsg("代�?生�?器�?支�?该页�?�风格");
			}

		}catch (Exception e) {
			e.printStackTrace();
			j.setMsg(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		try {
			String projectPath = URLEncoder.encode(subTableListEntity.getProjectPath(), "UTF-8");
			Cookie cookie = new Cookie("cookie_projectPath",projectPath );						
			cookie.setMaxAge(60*60*24*30); //cookie �?存30天
			response.addCookie(cookie);
			response.getWriter().print(j.getJsonStr());
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				response.getWriter().close();
			} catch (Exception e2) {
			}
		}
	}
	/**
	 * 获�?�智能表�?�中外键：根�?�是�?�设置了主表以�?�主表字段�?�判断
	 * @param colums
	 * @return
	 */
	private String[] getForeignkeys(List<CgFormFieldEntity> colums) {
		List<String> fs = new ArrayList<String>(0);
		for(CgFormFieldEntity c : colums){
			if(StringUtil.isNotEmpty(c.getMainTable()) && StringUtil.isNotEmpty(c.getMainField())){

//				fs.add(c.getFieldName().toUpperCase());
				fs.add(oConvertUtils.camelNameCapFirst(c.getFieldName()));

			}
		}
		String[] foreignkeys = (String[]) fs.toArray(new String[fs.size()]);
		return foreignkeys;
	}

	private String[] getCgformButtonSql(CgformButtonSqlEntity cbs){
		String[] newcgbsql=new String[]{};

//		if(cbs!=null){
//			StringBuffer cgb =new StringBuffer("");
//			String[] cgbsql=cbs.getCgbSqlStr().replaceAll("(\r\n|\r|\n|\n\r)", "").split(";");
//			for(int i=0;i<cgbsql.length;i++){
//				if(!("").equals(cgbsql[i].toString().trim())){
//					cgb.append(cgbsql[i]+";");
//				}
//			}
//			if(cgb.length()>0){
//				newcgbsql=cgb.toString().split(";");
//			}
//		}
		if(cbs!=null){
			if(StringUtils.isNotEmpty(cbs.getCgbSqlStr())){
				String sql = cbs.getCgbSqlStr().replaceAll("(\r\n|\r|\n|\n\r)", "");
				if(!("").equals(sql.toString().trim())){
					newcgbsql=new String[]{sql};
				}
			}
		}

		return newcgbsql;
	}

	/**
	 * 获�?�智能表�?�的所有�?置
	 * @param cgFormHead
	 * @param generateEntity
	 * @throws Exception 
	 */
	private void getCgformConfig(CgFormHeadEntity cgFormHead,
			GenerateEntity generateEntity) throws Exception {
		int filedNums = cgFormHead.getColumns().size();
		List<CgformButtonEntity> buttons = null;
		Map<String, String[]> buttonSqlMap = new LinkedHashMap<String, String[]>();
		//表�?��?置
		cgFormHead = cgFormFieldService.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
		//按钮�?置
		buttons = cgformButtonService.getCgformButtonListByFormId(cgFormHead.getId());

		//按钮SQL增强
		for(CgformButtonEntity cb:buttons){
			CgformButtonSqlEntity cbs = cgformButtonSqlService.getCgformButtonSqlByCodeFormId(cb.getButtonCode(), cgFormHead.getId());
			if(cbs !=null && oConvertUtils.isNotEmpty(cbs.getCgbSqlStr())){
				buttonSqlMap.put(cb.getButtonCode(),this.getCgformButtonSql(cbs));
			}
		}
		CgformButtonSqlEntity cbsAdd = cgformButtonSqlService.getCgformButtonSqlByCodeFormId("add", cgFormHead.getId());
		if(cbsAdd!=null && oConvertUtils.isNotEmpty(cbsAdd.getCgbSqlStr())){
			buttonSqlMap.put("add",this.getCgformButtonSql(cbsAdd));
		}
		CgformButtonSqlEntity cbsUpdate = cgformButtonSqlService.getCgformButtonSqlByCodeFormId("update", cgFormHead.getId());
		if(cbsUpdate!=null && oConvertUtils.isNotEmpty(cbsUpdate.getCgbSqlStr())){
			buttonSqlMap.put("update",this.getCgformButtonSql(cbsUpdate));
		}
		CgformButtonSqlEntity cbsDelete = cgformButtonSqlService.getCgformButtonSqlByCodeFormId("delete", cgFormHead.getId());
		if(cbsDelete!=null && oConvertUtils.isNotEmpty(cbsDelete.getCgbSqlStr())){
			buttonSqlMap.put("delete",this.getCgformButtonSql(cbsDelete));
		}

		
		//按钮java增强
		Map<String, CgformEnhanceJavaEntity> buttonJavaMap = new LinkedHashMap<String, CgformEnhanceJavaEntity>();
		List<CgformEnhanceJavaEntity> javaList = dataBaseService.getCgformEnhanceJavaEntityByFormId(cgFormHead.getId());
		if(javaList!=null&&javaList.size()>0){
			for(CgformEnhanceJavaEntity e:javaList){
				if(StringUtil.isNotEmpty(e.getCgJavaValue())){
					buttonJavaMap.put(e.getButtonCode(), e);
				}
			}
		}
		
		//JS增强-列表
		CgformEnhanceJsEntity listJs = 	cgformEnhanceJsService.getCgformEnhanceJsByTypeFormId("list", cgFormHead.getId());
		CgformEnhanceJsEntity listJsCopy = null;
		try{
			listJsCopy = listJs.deepCopy();
		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
		//JS增强-表�?�
		CgformEnhanceJsEntity formJs = 	cgformEnhanceJsService.getCgformEnhanceJsByTypeFormId("form", cgFormHead.getId());
		CgformEnhanceJsEntity formJsCopy = null;
		try{
			formJsCopy = formJs.deepCopy();
		}catch (Exception e) {
			logger.debug(e.getMessage());
		}
		//将js中带有online字段�??的 转�?��?java命�??
		for(CgFormFieldEntity field : cgFormHead.getColumns()){
			String fieldName = field.getFieldName();
			if(listJsCopy!=null){
				listJsCopy.setCgJsStr(listJsCopy.getCgJsStr().replace(fieldName, JeecgReadTable.formatField(fieldName)));
			}
			if(formJsCopy!=null&&formJsCopy.getCgJsStr()!=null){
				formJsCopy.setCgJsStr(formJsCopy.getCgJsStr().replace(fieldName, JeecgReadTable.formatField(fieldName)));
			}
			//online代�?生�?，popup对应的字典字段进行java命�??转�?�

			if("popup".equals(field.getShowType()) && oConvertUtils.isNotEmpty(field.getDictField())){
				field.setDictField(oConvertUtils.camelNames(field.getDictField()));
			}

		}
		generateEntity.setButtons(buttons);
		generateEntity.setButtonSqlMap(buttonSqlMap);
		generateEntity.setButtonJavaMap(buttonJavaMap);
		generateEntity.setCgFormHead(cgFormHead);
		generateEntity.setListJs(listJsCopy);
		generateEntity.setFormJs(formJsCopy);
	}
	/**
	 * 跳转到文件夹目录树
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goFileTree")
	public ModelAndView goFileTree(HttpServletRequest request) {
		return new ModelAndView("jeecg/cgform/generate/fileTree");
	}
	/**
	 * 返回�?目录json
	 * @param parentNode
	 * @return
	 */
	@RequestMapping(params = "doExpandFileTree")
	@ResponseBody
	public Object doExpandFileTree(String parentNode){
		JSONArray fjson = new JSONArray();
		try{
			if(StringUtil.isEmpty(parentNode)){
				//返回�?盘驱动器根目录
				File[] roots = File.listRoots();
				for(File r:roots){
					JSONObject item = new JSONObject();
					item.put("id", r.getAbsolutePath());
					item.put("text", r.getPath());
					item.put("iconCls", "icon-folder");
					if(hasDirs(r)){
						item.put("state", "closed");
					}else{
						item.put("state", "open");
					}
					fjson.add(item);
				}
			}else{
				try {
					parentNode =  new String(parentNode.getBytes("ISO-8859-1"), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				//返回�?目录集
				File parent = new File(parentNode);
				File[] chs = parent.listFiles();
				for(File r:chs){
					JSONObject item = new JSONObject();
					if(r.isDirectory()){
						item.put("id", r.getAbsolutePath());
						item.put("text", r.getPath());
						if(hasDirs(r)){
							item.put("state", "closed");
						}else{
							item.put("state", "open");
						}
						fjson.add(item);
					}else{
						
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("该文件夹�?�?�选择");
		}
		return fjson;
	}
	private boolean hasDirs(File dir){
		try{
			if(dir.listFiles().length==0){
	//			item.put("state", "open");
				return false;
			}else{
	//			item.put("state", "closed");
				return true;
			}
		}catch (Exception e) {
			logger.info(e.getMessage());
			return false;
		}
	}
}
