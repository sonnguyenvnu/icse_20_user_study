package org.jeecgframework.web.cgform.controller.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgform.common.CgAutoListConstant;
import org.jeecgframework.web.cgform.engine.TempletContext;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldVO;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.cgform.service.config.CgFormIndexServiceI;
import org.jeecgframework.web.cgform.service.config.DbTableHandleI;
import org.jeecgframework.web.cgform.service.impl.config.TableSQLServerHandleImpl;
import org.jeecgframework.web.cgform.service.impl.config.util.DbTableUtil;
import org.jeecgframework.web.cgform.service.impl.config.util.FieldNumComparator;
import org.jeecgframework.web.cgform.util.PublicUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: Controller
 * @Description: 智能表�?��?置
 * @author 屈然�?�
 * @date 2013-06-30 11:36:53
 * @version V1.0
 * 
 */
//@Scope("prototype")
@Controller
@RequestMapping("/cgFormHeadController")
public class CgFormHeadController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CgFormHeadController.class);
	
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	@Autowired
	private CgFormIndexServiceI cgFormIndexService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TempletContext templetContext;

	/**
	 * 自动生�?表属性列表 页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "cgFormHeadList")
	public ModelAndView cgFormHeadList(HttpServletRequest request) {
		return new ModelAndView("jeecg/cgform/config/cgFormHeadList");
	}
	/**
	 * �??供选择的界�?�
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "cgForms")
	public ModelAndView cgForms(HttpServletRequest request) {
		return new ModelAndView("jeecg/cgform/config/cgForms");
	}
	@RequestMapping(params = "goCgFormSynChoice")
	public ModelAndView goCgFormSynChoice(HttpServletRequest request) {
		return new ModelAndView("jeecg/cgform/config/cgformSynChoice");
	}

	@RequestMapping(params = "popmenulink")
	public ModelAndView popmenulink(ModelMap modelMap,
                                    @RequestParam String url,
                                    @RequestParam String title, HttpServletRequest request) {
        modelMap.put("title",title);
        modelMap.put("url",url);
		return new ModelAndView("jeecg/cgform/config/popmenulink");
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
	public void datagrid(CgFormHeadEntity cgFormHead,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CgFormHeadEntity.class,
				dataGrid);

		String jformCategory = request.getParameter("jformCategory");
		if(StringUtil.isNotEmpty(jformCategory)){
			cq.eq("jformCategory", jformCategory);
			//cq.add();
		}

		cq.isNull("physiceId");
		cq.add();

		
		// 查询�?�件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				cgFormHead);
		this.cgFormFieldService.getDataGridReturn(cq, true);

		List<CgFormHeadEntity> list = dataGrid.getResults();
		Map<String,Map<String,Object>> extMap = new HashMap<String, Map<String,Object>>();		
		List<Map<String,Object>> pzlist = this.cgFormFieldService.getPeizhiCountByIds(list);
		for(Map<String,Object> temp:pzlist){
	        //此为针对原�?�的行数�?�，拓展的新字段
			Map<String,Object> m = new HashMap<String,Object>();
	        m.put("hasPeizhi",temp.get("hasPeizhi")==null?"0":temp.get("hasPeizhi"));
	        extMap.put(temp.get("id").toString(), m);
		}
		//因数�?�查询优化，补全空数�?�。考虑到效率问题，�?使用嵌套循环。
		for(CgFormHeadEntity temp:list){
	        //此为针对原�?�的行数�?�，拓展的新字段
		    if (extMap.get(temp.getId())==null) {
		    	Map<String,Object> m = new HashMap<String,Object>();
				m.put("hasPeizhi","0");
			 	extMap.put(temp.getId(), m); 
			}       
		}
		
		TagUtil.datagrid(response, dataGrid, extMap);

	}

	/**
	 * 删除自动生�?表属性
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(CgFormHeadEntity cgFormHead,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cgFormHead = systemService.getEntity(CgFormHeadEntity.class,
				cgFormHead.getId());
		String message = "删除�?功";
		cgFormFieldService.deleteCgForm(cgFormHead);
		cgFormFieldService.removeSubTableStr4Main(cgFormHead);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		logger.info("["+IpUtil.getIpAddr(request)+"][online表�?��?置删除]"+message+"表�??："+cgFormHead.getTableName());
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 移除自动生�?表属性
	 * 
	 * @return
	 */
	@RequestMapping(params = "rem")
	@ResponseBody
	public AjaxJson rem(CgFormHeadEntity cgFormHead,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cgFormHead = systemService.getEntity(CgFormHeadEntity.class,
				cgFormHead.getId());
		String message = "移除�?功";
		cgFormFieldService.delete(cgFormHead);
		cgFormFieldService.removeSubTableStr4Main(cgFormHead);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		logger.info("["+IpUtil.getIpAddr(request)+"][online表�?��?置移除]"+message+"表�??："+cgFormHead.getTableName());
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 删除自动生�?表属性
	 * 
	 * @return
	 */
	@RequestMapping(params = "delField")
	@ResponseBody
	public AjaxJson delField(CgFormFieldEntity cgFormField,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		cgFormField = systemService.getEntity(CgFormFieldEntity.class,
				cgFormField.getId());
		String message = cgFormField.getFieldName()+"删除�?功";

		CgFormHeadEntity table = cgFormField.getTable();
		table.setIsDbSynch("N");
		this.cgFormFieldService.updateEntitie(table);

		cgFormFieldService.delete(cgFormField);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}

	
	/**
	 * �?�步表�?��?置到数�?�库
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doDbSynch")
	@ResponseBody
	public AjaxJson doDbSynch(CgFormHeadEntity cgFormHead,String synMethod,
			HttpServletRequest request) {
		String message;
		AjaxJson j = new AjaxJson();
		cgFormHead = systemService.getEntity(CgFormHeadEntity.class,cgFormHead.getId());

		logger.info("---�?�步数�?�库 ---doDbSynch-----> TableName:"+cgFormHead.getTableName()+" ---修改时间 :"+cgFormHead.getUpdateDate()+" ----创建时间:"+cgFormHead.getCreateDate() +"---请求IP ---+"+oConvertUtils.getIpAddrByRequest(request));
		//安全控制，判断�?在online管�?�中表�?��?�?许�?作
		String sql = "select count(*) from cgform_head where table_name = ?";
		Long i = systemService.getCountForJdbcParam(sql,cgFormHead.getTableName());
		if(i==0){
			message = "�?�步失败，�?�法无授�?�访问�?";
			logger.info(message+" ----- 请求IP ---+"+IpUtil.getIpAddr(request));
			j.setMsg(message);
			return j;
		}
		TSUser currentUser = ResourceUtil.getSessionUser();
        if(CgAutoListConstant.SYS_DEV_FLAG_0.equals(currentUser.getDevFlag())){
            message = "�?�步失败，当�?用户未授�?�开�?��?��?�?";
            logger.info(message+" ----- 请求IP ---+"+IpUtil.getIpAddr(request));
            j.setMsg(message);
            return j;
        }
        //TODO 校验登录用户是�?�拥有开�?��?��?

		
		//�?�步数�?�库
		try {
			if("force".equals(synMethod)){
				DbTableHandleI dbTableHandle = DbTableUtil.getTableHandle(systemService.getSession());
				if(dbTableHandle instanceof TableSQLServerHandleImpl){
					String dropsql =  dbTableHandle.dropTableSQL(cgFormHead.getTableName());
					systemService.executeSql(dropsql); 
				}
			}
			
			boolean bl = cgFormFieldService.dbSynch(cgFormHead,synMethod);
			if(bl){
				//追加主表的附表串
				cgFormFieldService.appendSubTableStr4Main(cgFormHead);

				//判断表�?�下是�?�有�?置表
				List<CgFormHeadEntity> list = cgFormFieldService.findByProperty(CgFormHeadEntity.class, "physiceId", cgFormHead.getId());
				if(list!=null&&list.size()>0){
					message = "�?�步�?功,当�?表�?�的�?置表�?�已被�?置";		
				}else{
					message = "�?�步�?功";
				}

				j.setMsg(message);
				logger.info("["+IpUtil.getIpAddr(request)+"][online表�?��?置�?�步数�?�库]"+message+"表�??："+cgFormHead.getTableName());
			}else{
				message = "�?�步失败";		
				j.setMsg(message);
				return j;
			}
		} catch (BusinessException e) {
			j.setMsg(e.getMessage());
			return j;
		}
		return j;
	}
	
	
	
	/**
	 * 添加自动生�?表属性
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(CgFormHeadEntity cgFormHead,
			HttpServletRequest request) {
		String message = "";

		templetContext.clearCache();

		AjaxJson j = new AjaxJson();
		CgFormHeadEntity oldTable =cgFormFieldService.getEntity(CgFormHeadEntity.class, cgFormHead.getId());
		cgFormFieldService.removeSubTableStr4Main(oldTable);
		//step.1 如果数�?�表已�?创建,则�?能更改主键策略(�?�续通过强制�?�步解决此问题)
		/*if(cgFormHead.getId()!=null){
			boolean tableexist = cgFormFieldService.checkTableExist(cgFormHead.getTableName());
			if(tableexist){
				if(!cgFormHead.getJformPkType().equalsIgnoreCase(oldTable.getJformPkType())){
					if((cgFormHead.getJformPkType().equalsIgnoreCase("NATIVE")||cgFormHead.getJformPkType().equalsIgnoreCase("SEQUENCE"))
							&&(oldTable.getJformPkType().equalsIgnoreCase("NATIVE")||oldTable.getJformPkType().equalsIgnoreCase("SEQUENCE"))){
						//native和sequence�?�以互转
					}else{
						throw new org.jeecgframework.core.common.exception.BusinessException("数�?�表已�?创建,�?能更�?�主键策略");
					}
				}
			}
		}
		*/

		/**
		 * 判断表�??在库中是�?�存在，防止创建�?�??表，冲掉原有系统表
		 */
		if(oConvertUtils.isEmpty(cgFormHead.getId())){
			String sql = "select count(*) from tmp_tables where wl_table_name = ?";
			long i = systemService.getCountForJdbcParam(sql, new String[]{cgFormHead.getTableName()});
			if(i>0){
				logger.info("["+IpUtil.getIpAddr(request)+"][系统已�?存在，online表�??："+cgFormHead.getTableName());
				j.setMsg("系统中已�?存在该表，�?�?许创建");
				return j;
			}
		}

		
		//step.2 判定表格是�?�存在
		StringBuffer msg = new StringBuffer();
		CgFormHeadEntity table = judgeTableIsNotExit(cgFormHead,oldTable,msg);
		message = msg.toString();
		//step.3 刷新orderNum并且去�?�?
		refreshFormFieldOrderNum(cgFormHead);
		
		
		if (StringUtil.isNotEmpty(cgFormHead.getId())&&table!=null) {
			List<CgFormFieldEntity>	formFieldEntities = table.getColumns();
			for (CgFormFieldEntity cgFormFieldEntity : formFieldEntities) {
				if (StringUtil.isEmpty(cgFormFieldEntity.getOldFieldName()) 
						&& StringUtil.isNotEmpty(cgFormFieldEntity.getFieldName())) {
					cgFormFieldEntity.setFieldName(cgFormFieldEntity.getFieldName().toLowerCase());
					cgFormFieldEntity.setOldFieldName(cgFormFieldEntity.getFieldName());
				}

				if (StringUtil.isNotEmpty(cgFormFieldEntity.getFieldName()))
					cgFormFieldEntity.setFieldName(cgFormFieldEntity.getFieldName().trim());

			}

			boolean isChange = cgFormIndexService.updateIndexes(cgFormHead);

			//isChange 索引是�?�更新
			cgFormFieldService.updateTable(table,null,isChange);

			cgFormFieldService.appendSubTableStr4Main(table);
			cgFormFieldService.sortSubTableStr(table);

			/**�?�步�?置表*/
			syncTable(table);

			
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else if (StringUtil.isEmpty(cgFormHead.getId())&&table==null) {
			List<CgFormFieldEntity>	formFieldEntities = cgFormHead.getColumns();
			for (CgFormFieldEntity cgFormFieldEntity : formFieldEntities) {
				if (StringUtil.isEmpty(cgFormFieldEntity.getOldFieldName())) {
					cgFormFieldEntity.setFieldName(cgFormFieldEntity.getFieldName().toLowerCase());
					cgFormFieldEntity.setOldFieldName(cgFormFieldEntity.getFieldName());
				}

				if (StringUtil.isNotEmpty(cgFormFieldEntity.getFieldName()))
					cgFormFieldEntity.setFieldName(cgFormFieldEntity.getFieldName().trim());

			}
			cgFormFieldService.saveTable(cgFormHead);

			cgFormIndexService.updateIndexes(cgFormHead);

			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		logger.info("["+IpUtil.getIpAddr(request)+"][online表�?��?置�?存]"+message+"表�??："+cgFormHead.getTableName());
		j.setMsg(message);
		return j;
	}

	
	
	/**
	 * 物�?�表修改�?��?�步�?置表
	 * @param table
	 */
	private void syncTable(CgFormHeadEntity table) {
		List<CgFormHeadEntity> headList = systemService.findByProperty(CgFormHeadEntity.class, "physiceId", table.getId());
		List<CgFormFieldEntity>	formFieldEntities = table.getColumns();
		if(headList!=null&&headList.size()>0){
			for (CgFormHeadEntity cgform : headList) {
				List<CgFormFieldEntity> fieldList = new ArrayList<CgFormFieldEntity>();
				List<CgFormFieldEntity> columns = cgform.getColumns();
				if(columns==null||columns.size()<=0){
					for (CgFormFieldEntity column : formFieldEntities) {
						CgFormFieldEntity field = new CgFormFieldEntity();
						field.setContent(column.getContent());
						field.setDictField(column.getDictField());
						field.setDictTable(column.getDictTable());
						field.setDictText(column.getDictText());
						field.setExtendJson(column.getExtendJson());
						field.setFieldDefault(column.getFieldDefault());
						field.setFieldHref(column.getFieldHref());
						field.setFieldLength(column.getFieldLength());
						field.setFieldName(column.getFieldName());
						field.setFieldValidType(column.getFieldValidType());
						field.setLength(column.getLength());

						field.setMainField(null);
						field.setMainTable(null);

						field.setOldFieldName(column.getOldFieldName());
						field.setOrderNum(column.getOrderNum());
						field.setPointLength(column.getPointLength());
						field.setQueryMode(column.getQueryMode());
						field.setShowType(column.getShowType());
						field.setType(column.getType());
						field.setIsNull(column.getIsNull());
						field.setIsShow(column.getIsShow());
						field.setIsShowList(column.getIsShowList());
						field.setIsKey(column.getIsKey());
						field.setIsQuery(column.getIsQuery());
						fieldList.add(field);
					}
				}else{
					for (CgFormFieldEntity cgFormFieldEntity : formFieldEntities) {
						if(columns!=null&&columns.size()>0){
							for (CgFormFieldEntity column : columns) {
								//相�?�添加，�?�?�的�?�?�处�?�
								if(cgFormFieldEntity.getFieldName().equals(column.getFieldName())){
									//相�?�，添加到新list,从原数�?�中remove;
									CgFormFieldEntity field = new CgFormFieldEntity();
									field.setContent(column.getContent());
									field.setDictField(column.getDictField());
									field.setDictTable(column.getDictTable());
									field.setDictText(column.getDictText());
									field.setExtendJson(column.getExtendJson());
									field.setFieldDefault(column.getFieldDefault());
									field.setFieldHref(column.getFieldHref());
									field.setFieldLength(column.getFieldLength());
									field.setFieldName(column.getFieldName());
									field.setFieldValidType(column.getFieldValidType());
									field.setLength(column.getLength());

									field.setMainField(null);
									field.setMainTable(null);

									field.setOldFieldName(column.getOldFieldName());
									field.setOrderNum(column.getOrderNum());
									field.setPointLength(column.getPointLength());
									field.setQueryMode(column.getQueryMode());
									field.setShowType(column.getShowType());
									field.setType(column.getType());
									field.setIsNull(cgFormFieldEntity.getIsNull());
									field.setIsShow(cgFormFieldEntity.getIsShow());
									field.setIsShowList(cgFormFieldEntity.getIsShowList());
									field.setIsKey(cgFormFieldEntity.getIsKey());
									field.setIsQuery(cgFormFieldEntity.getIsQuery());
									fieldList.add(field);
									columns.remove(column);
									//相�?�，就跳出进行下一次
									break;
								}else{
									CgFormFieldEntity field = new CgFormFieldEntity();
									field.setContent(cgFormFieldEntity.getContent());
									field.setDictField(cgFormFieldEntity.getDictField());
									field.setDictTable(cgFormFieldEntity.getDictTable());
									field.setDictText(cgFormFieldEntity.getDictText());
									field.setExtendJson(cgFormFieldEntity.getExtendJson());
									field.setFieldDefault(cgFormFieldEntity.getFieldDefault());
									field.setFieldHref(cgFormFieldEntity.getFieldHref());
									field.setFieldLength(cgFormFieldEntity.getFieldLength());
									field.setFieldName(cgFormFieldEntity.getFieldName());
									field.setFieldValidType(cgFormFieldEntity.getFieldValidType());
									field.setLength(cgFormFieldEntity.getLength());

									field.setMainField(null);
									field.setMainTable(null);

									field.setOldFieldName(cgFormFieldEntity.getOldFieldName());
									field.setOrderNum(cgFormFieldEntity.getOrderNum());
									field.setPointLength(cgFormFieldEntity.getPointLength());
									field.setQueryMode(cgFormFieldEntity.getQueryMode());
									field.setShowType(cgFormFieldEntity.getShowType());
									field.setType(cgFormFieldEntity.getType());
									field.setIsNull(cgFormFieldEntity.getIsNull());
									field.setIsShow(cgFormFieldEntity.getIsShow());
									field.setIsShowList(cgFormFieldEntity.getIsShowList());
									field.setIsKey(cgFormFieldEntity.getIsKey());
									field.setIsQuery(cgFormFieldEntity.getIsQuery());
									fieldList.add(field);
									columns.remove(column);
									//相�?�，就跳出进行下一次
									break;
								}
							}
						}else{
							CgFormFieldEntity field = new CgFormFieldEntity();
							field.setContent(cgFormFieldEntity.getContent());
							field.setDictField(cgFormFieldEntity.getDictField());
							field.setDictTable(cgFormFieldEntity.getDictTable());
							field.setDictText(cgFormFieldEntity.getDictText());
							field.setExtendJson(cgFormFieldEntity.getExtendJson());
							field.setFieldDefault(cgFormFieldEntity.getFieldDefault());
							field.setFieldHref(cgFormFieldEntity.getFieldHref());
							field.setFieldLength(cgFormFieldEntity.getFieldLength());
							field.setFieldName(cgFormFieldEntity.getFieldName());
							field.setFieldValidType(cgFormFieldEntity.getFieldValidType());
							field.setLength(cgFormFieldEntity.getLength());

							field.setMainField(null);
							field.setMainTable(null);

							field.setOldFieldName(cgFormFieldEntity.getOldFieldName());
							field.setOrderNum(cgFormFieldEntity.getOrderNum());
							field.setPointLength(cgFormFieldEntity.getPointLength());
							field.setQueryMode(cgFormFieldEntity.getQueryMode());
							field.setShowType(cgFormFieldEntity.getShowType());
							field.setType(cgFormFieldEntity.getType());
							field.setIsNull(cgFormFieldEntity.getIsNull());
							field.setIsShow(cgFormFieldEntity.getIsShow());
							field.setIsShowList(cgFormFieldEntity.getIsShowList());
							field.setIsKey(cgFormFieldEntity.getIsKey());
							field.setIsQuery(cgFormFieldEntity.getIsQuery());
							fieldList.add(field);
						}
					}
				}
				List<CgFormFieldEntity> colums = cgFormFieldService.findByProperty(CgFormFieldEntity.class, "table.id", cgform.getId());
				cgFormFieldService.deleteAllEntitie(colums);
				cgform.setColumns(fieldList);
				cgFormFieldService.saveTable(cgform);
			}
		}
	}

	
	/**
	 * 设置OrderNum
	 * @param cgFormHead
	 */
	private void refreshFormFieldOrderNum(CgFormHeadEntity cgFormHead) {
		Collections.sort(cgFormHead.getColumns(),new FieldNumComparator());
		for(int i = 0;i<cgFormHead.getColumns().size();i++){
			cgFormHead.getColumns().get(i).setOrderNum(i+1);
		}
	}

	/**
	 * 判断这个表格是�?是已�?存在
	 * 
	 * @param cgFormHead
	 * @param oldTable 
	 * @return
	 */
	private CgFormHeadEntity judgeTableIsNotExit(CgFormHeadEntity cgFormHead, CgFormHeadEntity oldTable,StringBuffer msg) {
		String message = "";
		CgFormHeadEntity table = cgFormFieldService.findUniqueByProperty(CgFormHeadEntity.class, "tableName",cgFormHead.getTableName());
		if (StringUtil.isNotEmpty(cgFormHead.getId())) {
			if(table != null && !oldTable.getTableName().equals(cgFormHead.getTableName())){
				message = "�?命�??的表已�?存在";
				table = null;
			}else{
				if(table == null){//�?命�??了表
					cgFormHead.setIsDbSynch("N");
				}
				table = table == null?oldTable:table;
				try {
					MyBeanUtils.copyBeanNotNull2Bean(cgFormHead, table);
				} catch (Exception e) {
					e.printStackTrace();
				}
				message = "修改�?功";
			}
		} else {
			message = table != null? "表已�?存在":"创建�?功";
		}
		msg.append(message);
		return table;
	}

	/**
	 * 自动生�?表属性列表页�?�跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(CgFormHeadEntity cgFormHead,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(cgFormHead.getId())) {
			cgFormHead = cgFormFieldService.getEntity(
					CgFormHeadEntity.class, cgFormHead.getId());
			//�?�用jform这个�?缀
			//cgFormHead.setTableName(cgFormHead.getTableName().replace(CgAutoListConstant.jform_, ""));
			req.setAttribute("cgFormHeadPage", cgFormHead);
		}

		List<TSType> typeList = ResourceUtil.getCacheTypes(MutiLangUtil.getLang("bdfl"));
		req.setAttribute("typeList", typeList);

		return new ModelAndView("jeecg/cgform/config/cgFormHead");
	}
	/**
	 * 获�?�表格具体的属性列
	 * 
	 * @return
	 */
	@RequestMapping(params = "getColumnList")
	@ResponseBody
	public List<CgFormFieldEntity> getColumnList(CgFormHeadEntity cgFormHead,String type,
			HttpServletRequest req) {
		
		List<CgFormFieldEntity> columnList = new ArrayList<CgFormFieldEntity>();
		if (StringUtil.isNotEmpty(cgFormHead.getId())) {
			CriteriaQuery cq = new CriteriaQuery(CgFormFieldEntity.class);
			cq.eq("table.id", cgFormHead.getId());
			cq.add();
			columnList = cgFormFieldService
					.getListByCriteriaQuery(cq, false);
			//对字段列按顺�?排�?
			Collections.sort(columnList,new FieldNumComparator());
		}else{
//			CgFormFieldEntity field = new CgFormFieldEntity();
//			field.setFieldName("id");
//			field.setLength(36);
//			field.setContent("主键");
//			field.setIsKey("Y");
//			field.setIsNull("N");
//			field.setOrderNum(1);
//			field.setType("string");
//			field.setPointLength(0);
//			field.setIsShow("N");
//			field.setIsShowList("N");
//			field.setFieldLength(120);
//			columnList.add(field);
			columnList=getInitDataList();
		}
		return columnList;
	}
	
	/**
	 * 添加�?始化列
	 * @return
	 */
	private List<CgFormFieldEntity>  getInitDataList(){
		List<CgFormFieldEntity> columnList = new ArrayList<CgFormFieldEntity>();
		
		columnList.add(initCgFormFieldEntityId());
		columnList.add(initCgFormFieldEntityString("create_name","创建人�??称"));
		columnList.add(initCgFormFieldEntityString("create_by", "创建人登录�??称"));
		columnList.add(initCgFormFieldEntityTime("create_date", "创建日期"));
		columnList.add(initCgFormFieldEntityString("update_name","更新人�??称"));
		columnList.add(initCgFormFieldEntityString("update_by", "更新人登录�??称"));
		columnList.add(initCgFormFieldEntityTime("update_date", "更新日期"));

		columnList.add(initCgFormFieldEntityString("sys_org_code","所属部门"));
		columnList.add(initCgFormFieldEntityString("sys_company_code", "所属公�?�"));

		columnList.add(initCgFormFieldEntityBpmStatus());

		return columnList;
	}
	/**
	 * 添加默认id
	 * @return
	 */
	private  CgFormFieldEntity  initCgFormFieldEntityId(){
		CgFormFieldEntity field = new CgFormFieldEntity();
		field.setFieldName("id");
		field.setLength(36);
		field.setContent("主键");
		field.setIsKey("Y");
		field.setIsNull("N");
		field.setOrderNum(1);
		field.setType("string");
		field.setPointLength(0);
		field.setIsShow("N");
		field.setIsShowList("N");
		field.setFieldLength(120);
		return field;
	}
	
	/**
	 * 添加默认id
	 * @return
	 */
	private  CgFormFieldEntity  initCgFormFieldEntityBpmStatus(){
		CgFormFieldEntity field = new CgFormFieldEntity();
		field.setFieldName("bpm_status");
		field.setLength(32);
		field.setContent("�?程状�?");
		field.setIsKey("N");
		field.setIsNull("Y");
		field.setOrderNum(1);
		field.setType("string");
		field.setPointLength(0);
		field.setIsShow("N");
		field.setIsShowList("Y");
		field.setFieldLength(120);
		field.setDictField("bpm_status");
		field.setFieldDefault("1");
		return field;
	}

	/**
	 * 添加默认数�?�
	 * @return
	 */
	private  CgFormFieldEntity  initCgFormFieldEntityString(String fieldName,String content){
		CgFormFieldEntity field = new CgFormFieldEntity();
		field.setFieldName(fieldName);
		field.setLength(50);
		field.setContent(content);
		field.setIsKey("N");
		field.setIsNull("Y");
		field.setOrderNum(2);
		field.setType("string");
		field.setPointLength(0);
		field.setIsShow("N");
		field.setIsShowList("N");
		field.setFieldLength(120);
		return field;
	}
	
	/**
	 * 添加默认时间
	 * @return
	 */
	private  CgFormFieldEntity  initCgFormFieldEntityTime(String fieldName,String content){
		CgFormFieldEntity field = new CgFormFieldEntity();
		field.setFieldName(fieldName);
		field.setLength(20);
		field.setContent(content);
		field.setIsKey("N");
		field.setIsNull("Y");
		field.setOrderNum(3);
		field.setType("Date");
		field.setPointLength(0);
		field.setIsShow("N");
		field.setIsShowList("N");
		field.setFieldLength(120);
		field.setShowType("date");
		return field;
	}
	/**
	 * 判断表格是够已�?创建
	 * 
	 * @return AjaxJson 中的success
	 */
	@RequestMapping(params = "checkIsExit")
	@ResponseBody
	public AjaxJson checkIsExit(String name,
			HttpServletRequest req) {
		AjaxJson j = new AjaxJson();

		//判断，如果是带有V字符的,截�?�获�?�真实表�??
		name = PublicUtil.replaceTableName(name);

		j.setSuccess(cgFormFieldService.judgeTableIsExit(name));
		return j;
	}
	/**
	 * sql�?�件 页�?�跳转
	 * @return
	 */
	@RequestMapping(params = "sqlPlugin")
	public ModelAndView sqlPlugin(String id,HttpServletRequest request) {
		CgFormHeadEntity bean = cgFormFieldService.getEntity(
				CgFormHeadEntity.class, id);
		request.setAttribute("bean", bean);
		return new ModelAndView("jeecg/cgform/config/cgFormSqlPlugin");
	}
	/**
	 * sql �?�件�?存
	 * @param id �?置id
	 * @param sql_plug_in �?�件内容
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "sqlPluginSave")
	@ResponseBody
	public AjaxJson sqlPluginSave(String id,String sql_plug_in,
			HttpServletRequest request) {
		String message = "";
		CgFormHeadEntity bean = cgFormFieldService.getEntity(
				CgFormHeadEntity.class, id);
		//bean.setSqlPlugIn(sql_plug_in);
		cgFormFieldService.updateTable(bean,null,false);
		message = "�?存�?功";
		systemService.addLog(message, Globals.Log_Type_INSERT,
				Globals.Log_Leavel_INFO);
		AjaxJson j =  new AjaxJson();
		j.setMsg(message);
		return j;
	}
	/**
	 * js�?�件 页�?�跳转
	 * @return
	 */
	@RequestMapping(params = "jsPlugin")
	public ModelAndView jsPlugin(String id,HttpServletRequest request) {
		CgFormHeadEntity bean = cgFormFieldService.getEntity(
				CgFormHeadEntity.class, id);
		request.setAttribute("bean", bean);
		return new ModelAndView("jeecg/cgform/config/cgFormJsPlugin");
	}
	/**
	 * js �?�件�?存
	 * @param id �?置id
	 * @param js_plug_in �?�件内容
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "jsPluginSave")
	@ResponseBody
	public AjaxJson jsPluginSave(String id,String js_plug_in,
			HttpServletRequest request) {
		String message = "";
		CgFormHeadEntity bean = cgFormFieldService.getEntity(
				CgFormHeadEntity.class, id);
		//bean.setJsPlugIn(js_plug_in);�?�用jsPlugIn这个字段
		cgFormFieldService.updateTable(bean,null,false);
		message = "�?存�?功";
		systemService.addLog(message, Globals.Log_Type_INSERT,
				Globals.Log_Leavel_INFO);
		AjaxJson j =  new AjaxJson();
		j.setMsg(message);
		return j;
	}
	
	
	@RequestMapping(params = "importExcel")
	@ResponseBody
	public AjaxJson importExcel(String headId,HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获�?�上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(0);
			params.setHeadRows(1);
			params.setNeedSave(false);
			try {
				CgFormHeadEntity cgFormHead = systemService.getEntity(CgFormHeadEntity.class,headId);
				if(cgFormHead==null){
					j.setMsg("表数�?�异常�?");
					return j;
				}
				List<CgFormFieldVO> fieldList =  ExcelImportUtil.importExcel(file.getInputStream(),CgFormFieldVO.class,params);
				//根�?�headid查询该表下的字段信�?�
				String hql = "from CgFormFieldEntity where table.id = ? ";
				List<CgFormFieldEntity> list = systemService.findHql(hql, headId);
				if(list==null){
					list = new ArrayList<CgFormFieldEntity>();
				}
				CgFormFieldEntity fieldEntity = null;
				StringBuilder sb = new StringBuilder("");
				List<CgFormFieldEntity> saveList =  new ArrayList<CgFormFieldEntity>();
				for (CgFormFieldVO field : fieldList) {
					//System.out.println("-------------field------------"+field);
					if(StringUtil.isEmpty(field.getFieldName())){
						continue;
					}
					if(existField(field.getFieldName(),list)){
						sb.append(field.getFieldName()).append(",");
						continue;
					}
					fieldEntity = new CgFormFieldEntity();
					fieldEntity.setTable(cgFormHead);
					fieldEntity.setFieldName(field.getFieldName());
					String content = field.getContent();
					if(StringUtil.isEmpty(content)){
						content = field.getFieldName();
					}
					fieldEntity.setContent(content);
					String type = field.getType();
					if(StringUtil.isEmpty(type)){
						type = "string";
					}
					fieldEntity.setType(type);
					String length = field.getLength();
					if(StringUtil.isEmpty(length)){
						length = "32";
					}
					fieldEntity.setLength(Integer.valueOf(length));
					String pointLength = field.getPointLength();
					if(StringUtil.isEmpty(pointLength)){
						pointLength = "0";
					}
					fieldEntity.setPointLength(Integer.valueOf(pointLength));
					fieldEntity.setFieldDefault(field.getFieldDefault());
					fieldEntity.setIsKey("N");
					String isNull = field.getIsNull();
					if("�?�".equals(isNull)){
						isNull = "N";
					}else{
						isNull = "Y";
					}
					fieldEntity.setIsNull(isNull);
					fieldEntity.setOrderNum(1);
					fieldEntity.setIsShow("Y");
					fieldEntity.setIsShowList("Y");
					fieldEntity.setFieldLength(120);
					//--author：zhoujf---start------date:20170207--------for:online表�?�  �?置表 导入字段 默认值处�?�
					fieldEntity.setIsQuery("N");
					fieldEntity.setShowType("text");
					fieldEntity.setOldFieldName(field.getFieldName());
					fieldEntity.setQueryMode("single");
					list.add(fieldEntity);
					saveList.add(fieldEntity);
				}
				systemService.batchSave(saveList);
				if(StringUtil.isEmpty(sb.toString())){
					j.setMsg("文件导入�?功�?");
				}else{
					j.setMsg("文件导入�?功�?�?�?字段�?"+sb.toString()+"】忽略");
				}
				
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
	
	private boolean existField(String field,List<CgFormFieldEntity> list){
		boolean flag = false;
		for(CgFormFieldEntity entity :list){
			if(field.equalsIgnoreCase(entity.getFieldName())){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * excel导入页�?�
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public String upload(String id,HttpServletRequest request) {
        request.setAttribute("headId", id);
		return "jeecg/cgform/config/cgformColUpload";
	}

	/**
	 * �?制物�?�表生�?�?置表
	 * copyOnline
	 */
	@RequestMapping(params = "copyOnline")
	@ResponseBody
	public AjaxJson copyOnline(String id,HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		if(StringUtil.isNotEmpty(id)){
			String hql = "select max(c.tableVersion) from CgFormHeadEntity c where c.physiceId = ?";
			List<Integer> versions = systemService.findHql(hql, id);
			if(versions.get(0)!=null){
				int version = versions.get(0); 
				CgFormHeadEntity cgFormHead = new CgFormHeadEntity();
				CgFormHeadEntity physicsTable = systemService.get(CgFormHeadEntity.class,id);
				cgFormHead.setTableName(physicsTable.getTableName()+CgAutoListConstant.ONLINE_TABLE_SPLIT_STR+(version+1+""));
				cgFormHead.setIsTree(physicsTable.getIsTree());
				cgFormHead.setContent(physicsTable.getContent());
				cgFormHead.setJformPkType(physicsTable.getJformPkType());
				cgFormHead.setJformPkSequence(physicsTable.getJformPkSequence());
				cgFormHead.setQuerymode(physicsTable.getQuerymode());
				cgFormHead.setIsCheckbox(physicsTable.getIsCheckbox());
				cgFormHead.setIsPagination(physicsTable.getIsPagination());

				cgFormHead.setJformType(1);//�?置表统一为�?�表

				cgFormHead.setJformCategory(physicsTable.getJformCategory());
				cgFormHead.setRelationType(physicsTable.getRelationType());

				cgFormHead.setSubTableStr(null);

				cgFormHead.setPhysiceId(physicsTable.getId());
				cgFormHead.setTabOrder(physicsTable.getTabOrder());
				cgFormHead.setTableVersion(version+1);
				cgFormHead.setTableType("1");
				cgFormHead.setIsDbSynch("N");
				cgFormHead.setJformVersion(physicsTable.getJformVersion());
				cgFormHead.setFormTemplate(physicsTable.getFormTemplate());
				cgFormHead.setFormTemplateMobile(physicsTable.getFormTemplateMobile());
				cgFormHead.setTreeFieldname(physicsTable.getTreeFieldname());
				cgFormHead.setTreeIdFieldname(physicsTable.getTreeIdFieldname());
				cgFormHead.setTreeParentIdFieldName(physicsTable.getTreeParentIdFieldName());
				List<CgFormFieldEntity> fieldList = new ArrayList<CgFormFieldEntity>();
				List<CgFormFieldEntity> columns = physicsTable.getColumns();
				for (CgFormFieldEntity f : columns) {
					CgFormFieldEntity field = new CgFormFieldEntity();
					field.setContent(f.getContent());
					field.setDictField(f.getDictField());
					field.setDictTable(f.getDictTable());
					field.setDictText(f.getDictText());
					field.setExtendJson(f.getExtendJson());
					field.setFieldDefault(f.getFieldDefault());
					field.setFieldHref(f.getFieldHref());
					field.setFieldLength(f.getFieldLength());
					field.setFieldName(f.getFieldName());
					field.setFieldValidType(f.getFieldValidType());
					field.setLength(f.getLength());

					field.setMainField(null);//默认为�?�表
					field.setMainTable(null);//默认为�?�表

					field.setOldFieldName(f.getOldFieldName());
					field.setOrderNum(f.getOrderNum());
					field.setPointLength(f.getPointLength());
					field.setQueryMode(f.getQueryMode());
					field.setShowType(f.getShowType());
					field.setType(f.getType());
					field.setIsNull(f.getIsNull());
					field.setIsShow(f.getIsShow());
					field.setIsShowList(f.getIsShowList());
					field.setIsKey(f.getIsKey());
					field.setIsQuery(f.getIsQuery());
					fieldList.add(field);
				}
				cgFormHead.setColumns(fieldList);
				cgFormFieldService.saveTable(cgFormHead);
					j.setObj(cgFormHead.getId());
					j.setMsg("新版本�?置表�?�:"+cgFormHead.getTableName()+"创建完�?");
					j.setSuccess(true);
					return j;
			}else{
				CgFormHeadEntity cgFormHead = new CgFormHeadEntity();
				CgFormHeadEntity physicsTable = systemService.get(CgFormHeadEntity.class,id);
				cgFormHead.setTableName(physicsTable.getTableName()+CgAutoListConstant.ONLINE_TABLE_SPLIT_STR+"0");
				cgFormHead.setIsTree(physicsTable.getIsTree());
				cgFormHead.setContent(physicsTable.getContent());
				cgFormHead.setJformPkType(physicsTable.getJformPkType());
				cgFormHead.setJformPkSequence(physicsTable.getJformPkSequence());
				cgFormHead.setQuerymode(physicsTable.getQuerymode());
				cgFormHead.setIsCheckbox(physicsTable.getIsCheckbox());
				cgFormHead.setIsPagination(physicsTable.getIsPagination());

				cgFormHead.setJformType(1);//�?置表统一为�?�表

				cgFormHead.setJformCategory(physicsTable.getJformCategory());
				cgFormHead.setRelationType(physicsTable.getRelationType());

				cgFormHead.setSubTableStr(null);

				cgFormHead.setPhysiceId(physicsTable.getId());
				cgFormHead.setTabOrder(physicsTable.getTabOrder());
				cgFormHead.setTableVersion(0);
				cgFormHead.setTableType("1");
				cgFormHead.setIsDbSynch("N");
				cgFormHead.setJformVersion(physicsTable.getJformVersion());
				cgFormHead.setFormTemplate(physicsTable.getFormTemplate());
				cgFormHead.setFormTemplateMobile(physicsTable.getFormTemplateMobile());
				cgFormHead.setTreeFieldname(physicsTable.getTreeFieldname());
				cgFormHead.setTreeIdFieldname(physicsTable.getTreeIdFieldname());
				cgFormHead.setTreeParentIdFieldName(physicsTable.getTreeParentIdFieldName());
				List<CgFormFieldEntity> fieldList = new ArrayList<CgFormFieldEntity>();
				List<CgFormFieldEntity> columns = physicsTable.getColumns();
				for (CgFormFieldEntity f : columns) {
					CgFormFieldEntity field = new CgFormFieldEntity();
					field.setContent(f.getContent());
					field.setDictField(f.getDictField());
					field.setDictTable(f.getDictTable());
					field.setDictText(f.getDictText());
					field.setExtendJson(f.getExtendJson());
					field.setFieldDefault(f.getFieldDefault());
					field.setFieldHref(f.getFieldHref());
					field.setFieldLength(f.getFieldLength());
					field.setFieldName(f.getFieldName());
					field.setFieldValidType(f.getFieldValidType());
					field.setLength(f.getLength());

					field.setMainField(null);
					field.setMainTable(null);

					field.setOldFieldName(f.getOldFieldName());
					field.setOrderNum(f.getOrderNum());
					field.setPointLength(f.getPointLength());
					field.setQueryMode(f.getQueryMode());
					field.setShowType(f.getShowType());
					field.setType(f.getType());
					field.setIsNull(f.getIsNull());
					field.setIsShow(f.getIsShow());
					field.setIsShowList(f.getIsShowList());
					field.setIsKey(f.getIsKey());
					field.setIsQuery(f.getIsQuery());
					fieldList.add(field);
				}
				cgFormHead.setColumns(fieldList);
				cgFormFieldService.saveTable(cgFormHead);
				j.setObj(cgFormHead.getId());
				j.setMsg("�?置表�?�:"+cgFormHead.getTableName()+"创建完�?");
				j.setSuccess(true);
				return j;
			}
		}
		return j;
	}
	/**
	 * 跳转到�?置表�?作页�?�
	 */
	@RequestMapping(params = "cgFormHeadConfigList")
	public ModelAndView cgFormHeadConfigList(String id,HttpServletRequest request) {
		if(StringUtil.isNotEmpty(id)){
			request.setAttribute("physiceId", id);
			return new ModelAndView("jeecg/cgform/config/cgFormHeadConfigList");
		}else{
			return null;
		}
	}
	
	/**
	 * �?置表加载数�?�
	 */
	@RequestMapping(params = "configDatagrid")
	public void configDatagrid(CgFormHeadEntity cgFormHead,String id,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {

		List<CgFormHeadEntity> findHql = null;
		if(oConvertUtils.isNotEmpty(cgFormHead.getTableName())) {
			String hql = "from CgFormHeadEntity c where c.physiceId = ? AND c.tableName = ? order by c.tableVersion asc";
			findHql = systemService.findHql(hql, id, cgFormHead.getTableName());
		} else {
			String hql = "from CgFormHeadEntity c where c.physiceId = ? order by c.tableVersion asc";
			findHql = systemService.findHql(hql, id);
		}

		dataGrid.setResults(findHql);
		dataGrid.setTotal(findHql.size());
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 校验是�?�存在�?置表
	 * 
	 */
	@RequestMapping(params = "getConfigId")
	@ResponseBody
	public AjaxJson getConfigId(String id,HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		if(StringUtil.isNotEmpty(id)){
			String hql = "from CgFormHeadEntity c where physiceId = ?";
			List<CgFormHeadEntity> cgformList = systemService.findHql(hql, id);
			if(cgformList!=null&&cgformList.size()>0){
				CgFormHeadEntity cgFormHeadEntity = cgformList.get(0);
				j.setSuccess(true);
				j.setObj(cgFormHeadEntity.getPhysiceId());
				return j;
			}else{
				j.setSuccess(false);
				j.setMsg("当�?表�?�无�?置表�?�");
				return j;
			}
		}
		return j;
	}

}
