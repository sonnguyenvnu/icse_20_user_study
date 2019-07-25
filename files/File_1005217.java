package org.jeecgframework.web.cgdynamgraph.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.online.def.CgReportConstant;
import org.jeecgframework.core.online.exception.CgReportNotFoundException;
import org.jeecgframework.core.online.util.CgReportQueryParamUtil;
import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.SqlInjectionUtil;
import org.jeecgframework.core.util.SqlUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.web.cgdynamgraph.service.core.CgDynamGraphServiceI;
import org.jeecgframework.web.cgreport.service.core.CgReportServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * @Title:CgReportController
 * @description: Onlineç§»åŠ¨æŠ¥è¡¨å±•ç¤º
 * @author scott
 * @date Jul 29, 2013 9:39:40 PM
 * @version V1.0
 */
@Controller
@RequestMapping("/cgDynamGraphController")
public class CgDynamGraphController extends BaseController {
	@Autowired
	private CgDynamGraphServiceI cgDynamGraphService;
	@Autowired
	private CgReportServiceI cgReportService;
	
	@RequestMapping(params = "design")
	public void design(String id, HttpServletRequest request,String gtype,
			HttpServletResponse response) {
		//step.1 æ ¹æ?®idèŽ·å?–è¯¥åŠ¨æ€?æŠ¥è¡¨çš„é…?ç½®å?‚æ•°
		Map<String, Object>  cgDynamGraphMap = null;
		try{
			cgDynamGraphMap = cgDynamGraphService.queryCgDynamGraphConfig(id);
		}catch (Exception e) {
			throw new CgReportNotFoundException("åŠ¨æ€?æŠ¥è¡¨é…?ç½®ä¸?å­˜åœ¨!");
		}
		//step.2 èŽ·å?–åˆ—è¡¨ftlæ¨¡æ?¿è·¯å¾„
		FreemarkerHelper viewEngine = new FreemarkerHelper();
		//step.3 ç»„å?ˆæ¨¡æ?¿+æ•°æ?®å?‚æ•°ï¼Œè¿›è¡Œé¡µé?¢å±•çŽ°
		loadVars(cgDynamGraphMap,request);
		String html;

		//åˆ¤æ–­æ˜¯å?¦ä¸ºç»¼å?ˆç±»
		Map<String, Object> mainConfig = (Map<String, Object> )cgDynamGraphMap.get(CgReportConstant.MAIN);
		String defaultGtype =mainConfig.get("graph_type")==null?null:(String)mainConfig.get("graph_type");
		//å›¾è¡¨ç±»åž‹å…¨ä¸ºç©ºï¼Œåˆ™è·³è½¬åˆ°ç»¼å?ˆç•Œé?¢ã€‚
		if(StringUtil.isEmpty(gtype)&&StringUtil.isEmpty(defaultGtype)){
			html = viewEngine.parseTemplate("/org/jeecgframework/web/cgdynamgraph/engine/core/cgDynamGraphDesign.ftl", cgDynamGraphMap);
		}
		//è·³è½¬åˆ°ç®€å?•é¡µé?¢ï¼Œå?ªæœ‰æŸ¥è¯¢æ?¡ä»¶ä¸Žå›¾
		else{
			gtype = StringUtils.isEmpty(gtype)?defaultGtype:gtype; //ä¼˜å…ˆçº§ï¼šå?‚æ•°ä¸­çš„å€¼æ¯”è¡¨å¤´é…?ç½®çš„å€¼é«˜
			cgDynamGraphMap.put("gtype", gtype);
			//è®¾ç½®gtype
			html = viewEngine.parseTemplate("/org/jeecgframework/web/cgdynamgraph/engine/core/cgDynamGraphDesignMobile.ftl", cgDynamGraphMap);
		}

		try {
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter writer = response.getWriter();
			writer.println(html);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				response.getWriter().close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
	private String getHtmlHead(HttpServletRequest request){
		HttpSession session = ContextHolderUtils.getSession();
		String lang = (String)session.getAttribute("lang");
		StringBuilder sb= new StringBuilder("");
		SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme(request);
		sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/jquery-plugs/i18n/jquery.i18n.properties.js\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>");
		sb.append(SysThemesUtil.getEasyUiTheme(sysThemesEnum));
		sb.append("<link rel=\"stylesheet\" href=\"plug-in/easyui/themes/icon.css\" type=\"text/css\"></link>");
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/accordion.css\">");
		sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/accordion/css/icons.css\">");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/easyui/locale/zh-cn.js\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>");
		sb.append(SysThemesUtil.getLhgdialogTheme(sysThemesEnum));

		sb.append("<script type=\"text/javascript\" src=\"plug-in/layer/layer.js\"></script>");

		sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/curdtools.js\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/easyuiextend.js\"></script>");
		return sb.toString();
	}
	
	/**
	 * ç»„è£…æ¨¡ç‰ˆå?‚æ•°
	 * @param cgDynamGraphMap
	 */
	@SuppressWarnings("unchecked")
	private void loadVars(Map<String, Object> cgDynamGraphMap,HttpServletRequest request) {
		Map mainM = (Map) cgDynamGraphMap.get(CgReportConstant.MAIN);
		List<Map<String,Object>> fieldList = (List<Map<String, Object>>) cgDynamGraphMap.get(CgReportConstant.ITEMS);
		List<String> paramList = (List<String>)cgDynamGraphMap.get(CgReportConstant.PARAMS);
		List<Map<String,Object>> queryList = new ArrayList<Map<String,Object>>(0);
		for(Map<String,Object> fl:fieldList){
			fl.put(CgReportConstant.ITEM_FIELDNAME, ((String)fl.get(CgReportConstant.ITEM_FIELDNAME)).toLowerCase());
			String isQuery = (String) fl.get(CgReportConstant.ITEM_ISQUERY);
			if(CgReportConstant.BOOL_TRUE.equalsIgnoreCase(isQuery)){
				cgReportService.loadDic(fl);
				queryList.add(fl);
			}
		}
		StringBuilder sb = new StringBuilder("");
		if(paramList!=null&&paramList.size()>0){
//			queryList = new ArrayList<Map<String,Object>>(0);
			for(String param:paramList){
				sb.append("&").append(param).append("=");
				String value = request.getParameter(param);
    			if(StringUtil.isNotEmpty(value)){
    				sb.append(value);
    			}
			}
		}
		cgDynamGraphMap.put(CgReportConstant.CONFIG_ID, mainM.get("code"));
		cgDynamGraphMap.put(CgReportConstant.CONFIG_NAME, mainM.get("name"));
		cgDynamGraphMap.put(CgReportConstant.CONFIG_FIELDLIST, fieldList);
		cgDynamGraphMap.put(CgReportConstant.CONFIG_QUERYLIST, queryList);
		//èŽ·å?–ä¼ é€’å?‚æ•°
		cgDynamGraphMap.put(CgReportConstant.CONFIG_PARAMS, sb.toString());
	}
	
	
	/**
	 * åŠ¨æ€?æŠ¥è¡¨æ•°æ?®æŸ¥è¯¢
	 * @param configId é…?ç½®id-code
	 * @param page åˆ†é¡µé¡µé?¢
	 * @param rows åˆ†é¡µå¤§å°?
	 * @param request 
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagrid")
	public void datagrid(String configId,HttpServletRequest request,
			HttpServletResponse response) {
		//step.1 æ ¹æ?®idèŽ·å?–è¯¥åŠ¨æ€?æŠ¥è¡¨çš„é…?ç½®å?‚æ•°
		Map<String, Object>  cgDynamGraphMap = null;
		try{
			cgDynamGraphMap = cgDynamGraphService.queryCgDynamGraphConfig(configId);
			if(cgDynamGraphMap.size()<=0){
				throw new CgReportNotFoundException("åŠ¨æ€?æŠ¥è¡¨é…?ç½®ä¸?å­˜åœ¨!");
			}
		}catch (Exception e) {
			throw new CgReportNotFoundException("æŸ¥æ‰¾åŠ¨æ€?æŠ¥è¡¨é…?ç½®å¤±è´¥!"+e.getMessage());
		}
		//step.2 èŽ·å?–è¯¥é…?ç½®çš„æŸ¥è¯¢SQL
		Map configM = (Map) cgDynamGraphMap.get(CgReportConstant.MAIN);
		String querySql = (String) configM.get(CgReportConstant.CONFIG_SQL);
		List<Map<String,Object>> items = (List<Map<String, Object>>) cgDynamGraphMap.get(CgReportConstant.ITEMS);
		List<String> paramList = (List<String>) cgDynamGraphMap.get(CgReportConstant.PARAMS);
		//é¡µé?¢å?‚æ•°æŸ¥è¯¢å­—æ®µï¼ˆå? ä½?ç¬¦çš„æ?¡ä»¶è¯­å?¥ï¼‰
		Map pageSearchFields =  new LinkedHashMap<String,Object>();

		//èŽ·å?–æŸ¥è¯¢æ?¡ä»¶æ•°æ?®
		Map<String,Object> paramData = new HashMap<String, Object>();
		if(paramList!=null&&paramList.size()>0){
			for(String param :paramList){
				String value = request.getParameter(param);
				value = value==null?"":value;

//				SqlInjectionUtil.filterContent(value);

//				querySql = querySql.replace("${"+param+"}", value);
				
				querySql = querySql.replace("'${"+param+"}'", ":"+param);
				querySql = querySql.replace("${"+param+"}", ":"+param);
				paramData.put(param, value);
			}
		}

		for(Map<String,Object> item:items){
			String isQuery = (String) item.get(CgReportConstant.ITEM_ISQUERY);
			if(CgReportConstant.BOOL_TRUE.equalsIgnoreCase(isQuery)){
				//step.3 è£…è½½æŸ¥è¯¢æ?¡ä»¶
				CgReportQueryParamUtil.loadQueryParams(request, item, pageSearchFields,paramData);
			}
		}

		//step.4 è¿›è¡ŒæŸ¥è¯¢è¿”å›žç»“æžœ

        String dbKey=(String)configM.get("db_source");
        List<Map<String, Object>> result=null;
        Long size=0l;
        if(StringUtils.isNotBlank(dbKey)){

        	Map map= null;
        	if(paramData!=null&&paramData.size()>0){
        		result= DynamicDBUtil.findListByHash(dbKey,SqlUtil.getFullSql(querySql,pageSearchFields),(HashMap<String, Object>)paramData);
        		map=(Map)DynamicDBUtil.findOneByHash(dbKey,SqlUtil.getCountSql(querySql,pageSearchFields),(HashMap<String, Object>)paramData);
        	}else{
        		result= DynamicDBUtil.findList(dbKey,querySql);
        		map=(Map)DynamicDBUtil.findOne(dbKey,SqlUtil.getCountSql(querySql,null));
        	}

            if(map.get("COUNT(*)") instanceof BigDecimal){
            	BigDecimal count = (BigDecimal)map.get("COUNT(*)");
            	size = count.longValue();
            }else{
            	size=(Long)map.get("COUNT(*)");
            }
        }else{

            result= cgDynamGraphService.queryByCgDynamGraphSql(querySql, pageSearchFields,paramData);
            size = cgDynamGraphService.countQueryByCgDynamGraphSql(querySql, pageSearchFields,paramData);

        }

        cgReportService.dealDic(result,items);
        cgReportService.dealReplace(result,items);
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.println(CgReportQueryParamUtil.getJson(result,size));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	/**
	 * è§£æž?SQLï¼Œè¿”å›žå­—æ®µé›†
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "getFields", method = RequestMethod.POST)
	@ResponseBody
	public Object getSqlFields(String sql,String dbKey){
		List<String> fields = null;
		List<String> params = null;
		Map reJson = new HashMap<String, Object>();
		try{
			fields = cgReportService.getFields(sql, dbKey);
			params = cgReportService.getSqlParams(sql);
		}catch (Exception e) {
			e.printStackTrace();
			String errorInfo = "è§£æž?å¤±è´¥!<br><br>å¤±è´¥åŽŸå› ï¼š";

			//æ— æ³•ç›´æŽ¥æ?•æ?‰åˆ°:java.net.ConnectExceptionå¼‚å¸¸
			int i = e.getMessage().indexOf("Connection refused: connect");
			
			if (i != -1) {//é?žé“¾æŽ¥å¼‚å¸¸
				errorInfo += "æ•°æ?®æº?è¿žæŽ¥å¤±è´¥.";
			}else{
				errorInfo += "SQLè¯­æ³•é”™è¯¯.";
			}

			reJson.put("status", "error");
			reJson.put("datas", errorInfo);
			return reJson;
		}
		reJson.put("status", "success");
		reJson.put("fields", fields);
		reJson.put("params", params);
		return reJson;
	}
	
}
