
package org.jeecgframework.tag.core.easyui;

import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.jeecgframework.core.util.StringUtil;


/**
 * 类�??述：MutiLang标签处�?�类
 * 
 * @author 高留刚
 * @date： 日期：2012-12-7 时间：上�?�10:17:45
 * @version 1.0
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked", "static-access" })
public class MutiLangTag extends TagSupport {
	protected String langKey;
	protected String langArg;
	
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.clear();
				out.close();
			} catch (Exception e2) {
			}
		}
		return EVAL_PAGE;
	}

	public String end() {

		MutiLangServiceI mutiLangService = ApplicationContextUtil.getContext().getBean(MutiLangServiceI.class);	

		String lang_context = mutiLangService.getLang(langKey, langArg);
		return lang_context;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}
	
	public void setLangArg(String langArg) {
		this.langArg = langArg;
	}
}
