package org.jeecgframework.web.cgform.engine;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.web.cgform.common.CgAutoListConstant;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveModel;

@Component("templetContext")
public class TempletContext {
	private static final Logger log = LoggerFactory.getLogger(TempletContext.class);
	
	@Resource(name = "freemarker")
	private Configuration freemarker;
	
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	private Map<String, TemplateDirectiveModel> tags;
	private static final String ENCODING = "UTF-8";

	@Autowired
	private CacheServiceI cacheService;
	
	/**
	 * ç³»ç»Ÿæ¨¡å¼?ï¼š
	 * PUB-ç”Ÿäº§ï¼ˆä½¿ç”¨ehcacheï¼‰
	 * DEV-å¼€å?‘
	 */
	public static String _sysMode = null;
	static{
		PropertiesUtil util = new PropertiesUtil("sysConfig.properties");
		_sysMode = util.readProperty(CgAutoListConstant.SYS_MODE_KEY);
	}

	@PostConstruct
	public void init() {
		if (tags == null)
			return;
		for (String key : tags.keySet()) {
			freemarker.setSharedVariable(key, tags.get(key));
		}
	}

	public Locale getLocale() {
		return freemarker.getLocale();
	}

	public Template getTemplate(String tableName, String ftlVersion) {
		Template template = null;
		if (tableName == null) {
			return null;
		}
		String oldTableName = tableName;
		//æ ¹æ?®ftlVersionåŠ¨æ€?è¯»å?–æ¨¡æ?¿[æŒ‡å®šwordæ¨¡æ?¿å?·]
        if (ftlVersion != null && ftlVersion.length() > 0) {
            tableName = tableName + "&ftlVersion=" + ftlVersion;
        }
        
        try {
			if(CgAutoListConstant.SYS_MODE_DEV.equalsIgnoreCase(_sysMode)){//å¼€å?‘æ¨¡å¼?
				template = freemarker.getTemplate(tableName,freemarker.getLocale(), ENCODING);
			}else if(CgAutoListConstant.SYS_MODE_PUB.equalsIgnoreCase(_sysMode)){//å?‘å¸ƒæ¨¡å¼?ï¼ˆç¼“å­˜ï¼‰
				//èŽ·å?–ç‰ˆæœ¬å?·
		    	String version = cgFormFieldService.getCgFormVersionByTableName(oldTableName);
				template = getTemplateFromCache(tableName, ENCODING,version);
			}else{
				throw new RuntimeException("sysConfig.propertiesçš„freeMarkerModeé…?ç½®é”™è¯¯ï¼š(PUB:ç”Ÿäº§æ¨¡å¼?ï¼ŒDEV:å¼€å?‘æ¨¡å¼?)");
			}
			return template;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * ä»Žç¼“å­˜ä¸­è¯»å?–ftlæ¨¡æ?¿
	 * @param template
	 * @param encoding
	 * @version onlineè¡¨é…?ç½®ç‰ˆæœ¬å?·
	 * @return
	 */
	public Template getTemplateFromCache(String tableName,String encoding,String version){
		Template template =  null;
		try {
			//cacheçš„é”®ï¼šç±»å??.æ–¹æ³•å??.å?‚æ•°å??.version
			String cacheKey = this.getClass().getSimpleName()+".getTemplateFormCache."+tableName+"."+version;;
			Object templateObj = cacheService.get(CacheServiceI.SYSTEM_BASE_CACHE,cacheKey);
			if(templateObj==null){
				template = freemarker.getTemplate(tableName,freemarker.getLocale(), ENCODING);
				cacheService.put(CacheServiceI.SYSTEM_BASE_CACHE,cacheKey,template);
				log.info("--setTemplateFromCache-------cacheKey: [{}]-------------",cacheKey);
			}else{
				log.info("--getTemplateFromCache-------cacheKey: [{}]-------------",cacheKey);
				template = (Template)templateObj;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return template;
	}
	
	/**
	 * ä»Žç¼“å­˜ä¸­è¯»å?–ftlæ¨¡æ?¿
	 * @param template
	 * @param encoding
	 * @return
	 */
	public void removeTemplateFromCache(String tableName){
		try {
			//èŽ·å?–ç‰ˆæœ¬å?·
	    	String version = cgFormFieldService.getCgFormVersionByTableName(tableName);
			//cacheçš„é”®ï¼šç±»å??.æ–¹æ³•å??.å?‚æ•°å??
			String cacheKey = FreemarkerHelper.class.getName()+".getTemplateFormCache."+tableName+"."+version;
			cacheService.remove(CacheServiceI.SYSTEM_BASE_CACHE,cacheKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Configuration getFreemarker() {
		return freemarker;
	}

	public void setFreemarker(Configuration freemarker) {
		this.freemarker = freemarker;
	}

	public Map<String, TemplateDirectiveModel> getTags() {
		return tags;
	}

	public void setTags(Map<String, TemplateDirectiveModel> tags) {
		this.tags = tags;
	}
	/**
	 * æ¸…ç©ºonlineç¼“å­˜
	 */
	public void clearCache(){
		cacheService.clean(CacheServiceI.SYSTEM_BASE_CACHE);
	}
}
