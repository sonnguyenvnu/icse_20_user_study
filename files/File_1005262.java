package org.jeecgframework.web.system.controller.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysDatabaseEnum;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
import org.jeecgframework.web.system.service.DynamicDataSourceServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: Controller
 * @Description: æ•°æ?®æº?é…?ç½®
 * @author zhangdaihao
 * @date 2014-09-05 13:22:10
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/dynamicDataSourceController")
public class DynamicDataSourceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DynamicDataSourceController.class);

	@Autowired
	private DynamicDataSourceServiceI dynamicDataSourceService;
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
	 * æ•°æ?®æº?é…?ç½®åˆ—è¡¨ é¡µé?¢è·³è½¬
	 *
	 * @return
	 */
	@RequestMapping(params = "dbSource")
	public ModelAndView dbSource(HttpServletRequest request) {
		return new ModelAndView("system/dbsource/dbSourceList");
	}

	/**
	 * easyui AJAXè¯·æ±‚æ•°æ?®
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(DynamicDataSourceEntity dbSource,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DynamicDataSourceEntity.class, dataGrid);
		//æŸ¥è¯¢æ?¡ä»¶ç»„è£…å™¨
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, dbSource, request.getParameterMap());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);

	}

	/**
	 * åˆ é™¤æ•°æ?®æº?é…?ç½®
	 *
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DynamicDataSourceEntity dbSource, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		dbSource = systemService.getEntity(DynamicDataSourceEntity.class, dbSource.getId());

		message = MutiLangUtil.paramDelSuccess("common.datasource.manage");

		systemService.delete(dbSource);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}


	/**
	 * æ·»åŠ æ•°æ?®æº?é…?ç½®
	 *
	 * @param dbSource
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DynamicDataSourceEntity dbSource, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(dbSource.getId())) {
			message = MutiLangUtil.paramUpdSuccess("common.datasource.manage");
			DynamicDataSourceEntity t = systemService.get(DynamicDataSourceEntity.class, dbSource.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(dbSource, t);

				t.setDbPassword(PasswordUtil.encrypt(t.getDbPassword(), t.getDbUser(), PasswordUtil.getStaticSalt()));

				systemService.saveOrUpdate(t);
				dynamicDataSourceService.refleshCache();
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = MutiLangUtil.paramUpdFail("common.datasource.manage");
			}
		} else {
			message = MutiLangUtil.paramAddSuccess("common.datasource.manage");

			try {
				dbSource.setDbPassword(PasswordUtil.encrypt(dbSource.getDbPassword(), dbSource.getDbUser(), PasswordUtil.getStaticSalt()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			systemService.save(dbSource);
			dynamicDataSourceService.refleshCache();
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * æ•°æ?®æº?é…?ç½®åˆ—è¡¨é¡µé?¢è·³è½¬
	 *
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DynamicDataSourceEntity dbSource, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(dbSource.getId())) {
			dbSource = systemService.getEntity(DynamicDataSourceEntity.class, dbSource.getId());

			try {
				//String result = PasswordUtil.decrypt(d.getDbPassword(), d.getDbUser(), PasswordUtil.getStaticSalt());
				//System.out.println("==result"+result);
				//ç›´æŽ¥dbSource.setDbPassword hibernateä¼šè‡ªåŠ¨ä¿?å­˜ä¿®æ”¹ï¼Œæ•°æ?®åº“å€¼éš?ä¹‹æ”¹å?˜ï¼Œå› æ­¤é‡‡ç”¨ä¸´æ—¶å?˜é‡?æ–¹å¼?ä¼ é€’åˆ°é¡µé?¢
				String showDbPassword = PasswordUtil.decrypt(dbSource.getDbPassword(), dbSource.getDbUser(), PasswordUtil.getStaticSalt());//è§£å¯†dbPassword
				req.setAttribute("showDbPassword", showDbPassword);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			req.setAttribute("dbSourcePage", dbSource);
		}
		return new ModelAndView("system/dbsource/dbSource");
	}


    /**
     * èŽ·å?–æ•°æ?®æº?åˆ—è¡¨
     * @return
     */
    @RequestMapping(params = "getAll")
    @ResponseBody
    public List<ComboBox> getAll(){
        List<DynamicDataSourceEntity> list= systemService.getList(DynamicDataSourceEntity.class);
        List<ComboBox> comboBoxes=new ArrayList<ComboBox>();
        if(list!=null&&list.size()>0){
            for(DynamicDataSourceEntity entity:list){
                ComboBox comboBox=new ComboBox();
                comboBox.setId(entity.getId());
                comboBox.setText(entity.getDbKey());
                comboBoxes.add(comboBox);
            }
        }
        return  comboBoxes;
    }


    @RequestMapping(params = "getDynamicDataSourceParameter")
	@ResponseBody
    public AjaxJson getDynamicDataSourceParameter(@RequestParam String dbType){
    	AjaxJson j = new AjaxJson();
    	SysDatabaseEnum sysDatabaseEnum = SysDatabaseEnum.toEnum(dbType);

    	if (sysDatabaseEnum != null) {
    		Map<String, String> map = new HashMap<String, String>();
        	map.put("driverClass", sysDatabaseEnum.getDriverClass());
        	map.put("url", sysDatabaseEnum.getUrl());
        	map.put("dbtype", sysDatabaseEnum.getDbtype());
        	j.setObj(map);
		}else {
			j.setObj("");
		}

    	return j;
    }

    @RequestMapping(params = "testConnection")
	@ResponseBody
    public AjaxJson testConnection(DynamicDataSourceEntity dbSource, HttpServletRequest request){
    	AjaxJson j = new AjaxJson();
    	Connection con = null;
    	Map map = new HashMap();
    	try {
			Class.forName(dbSource.getDriverClass());//åŠ è½½å?Šæ³¨å†ŒJDBCé©±åŠ¨ç¨‹åº?
			//å»ºç«‹è¿žæŽ¥å¯¹è±¡
			con = DriverManager.getConnection(dbSource.getUrl(), dbSource.getDbUser(), dbSource.getDbPassword());
			if(con!=null){
				map.put("msg", "æ•°æ?®åº“è¿žæŽ¥æˆ?åŠŸ!!");
			}
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			logger.error(e.toString());
			map.put("msg", "æ•°æ?®åº“è¿žæŽ¥å¤±è´¥!!");
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.error(e.toString());
			map.put("msg", "æ•°æ?®åº“è¿žæŽ¥å¤±è´¥!!");
		}finally{
			try {
				if(con!=null&&!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {
				//e.printStackTrace();
				logger.error(e.toString());
			}
		}
    	j.setObj(map);
    	return j;
    }

    
}
