package org.jeecgframework.tag.core.easyui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.MenuButtonsEnum;
import org.jeecgframework.core.online.util.FreemarkerHelper;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.service.SystemService;
/**
 * 
 * @Title:MenuButtonTag
 * @description:æ–°æ?ƒé™?JSPæ ‡ç­¾ï¼Œé€šè¿‡æ ‡ç­¾çš„ â€œæ?ƒé™?æ ‡ç¤ºâ€? æŽ§åˆ¶ä»£ç ?ç‰‡æ®µæ˜¯å?¦åŠ è½½
 * @version V1.0
 * ã€?èˆ¹èˆ¶ä¸“ç”¨ã€‘
 * æ?ƒé™?åˆ¤æ–­ name-code
 * 1.æ²¡æœ‰é…?ç½®æ?ƒé™?è§„åˆ™ åˆ™ç›´æŽ¥åŠ è½½
 * 2.é…?ç½®äº†ä½†æ˜¯æ²¡æœ‰å‹¾é€‰ ä¸?åŠ è½½
 * 3.é…?ç½®äº†ä¸”å‹¾é€‰äº† åŠ è½½
 */
public class MenuButtonsTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	
	/**æŽ§ä»¶name-å”¯ä¸€æ ‡è¯†*/
	private String name;
	/**
	 * é€—å?·éš”å¼€ é…?ç½®ALLåˆ™åŠ è½½æ‰€æœ‰
	 */
	private String codes;
	/**
	 * æ˜¯å?¦å?–å??
	 */
	private boolean notIn = false;
	/**
	 * æ˜¯å?¦ä¸»è¡¨è?œå?•mainMenu
	 */
	private boolean mm = false;
	
	private boolean group = false;
	
	private boolean superQuery = false;
	
	private SystemService systemService;
	
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspTagException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			out.print(end());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.clear();
					out.close();
				} catch (Exception e) {
				}
			}
			
		}
		return EVAL_PAGE;
	}
	
	private String end(){
		if(oConvertUtils.isEmpty(codes)){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		List<String> optcodes = null;
		boolean flag = false;
		if(ResourceUtil.getSessionUser().getUserName().equals("admin")|| !Globals.BUTTON_AUTHORITY_CHECK){
			flag = true;
		}else{
			optcodes= getOperationcodes();
			if(optcodes==null || optcodes.size()<=0){
				flag = true;
			}
		}
		if(superQuery){
			addAdvancedQuery(sb, codes, name);
		}
		
		if(codes.equals("ALL")){
			//æ‰€æœ‰è?œå?•å?‡åŠ è½½
			MenuButtonsEnum[] menuArr = MenuButtonsEnum.values();
			for (int k = menuArr.length;k>0;k--) {
				MenuButtonsEnum menu = menuArr[k-1];
				if(flag){
					initMenu(sb, menu);
				}else{
					if(hasAuth(menu.getCode(), optcodes)){
						initMenu(sb, menu);
					}
				}
			}
		}else{
			if(isNotIn()){
				//åŠ è½½é?ž
				MenuButtonsEnum[] menuArr = MenuButtonsEnum.values();
				for (int k = menuArr.length;k>0;k--) {
					MenuButtonsEnum menu = menuArr[k-1];
					//TODO ç”¨çš„æ˜¯indexOfæœ‰éš?æ‚£
					if(codes.indexOf(menu.getCode())<0){
						if(flag){
							initMenu(sb, menu);
						}else{
							if(hasAuth(menu.getCode(), optcodes)){
								initMenu(sb, menu);
							}
						}
					}
				}
			}else{
				String[] codeArr = codes.split(",");
				for (int i = codeArr.length;i>0;i--) {
					String c = codeArr[i-1];
					MenuButtonsEnum menu = MenuButtonsEnum.getMenuByCode(c);
					if(menu==null){
						continue;
					}
					if(flag){
						initMenu(sb, menu);
					}else{
						if(hasAuth(c , optcodes)){
							initMenu(sb, menu);
						}
					}
				}
			}
		}
		//System.out.println(name+"--"+sb.toString());
		if(this.group){
			loadGroupJs(sb);
		}
		return sb.toString();
		
	}
	/**
	 * åŠ è½½å?•ä¸ªè?œå?•HTMLç‰‡æ®µ
	 * @param sb
	 * @param menu
	 */
	private void initMenu(StringBuffer sb,MenuButtonsEnum menu){
		if(!"superQuery".equals(menu.getFun())){
			String arg = mm?"1":"";
			if(menu.getCode().contains("group")){
				this.group=true;
				String funs[] = menu.getFun().split("&");
				String titles[] = menu.getTitle().split("&");
				String icons[] = menu.getIcon().split("&");
				sb.append("<div class='awesome-group'>");
				sb.append("<a onclick='"+funs[0]+"("+arg+")' href='####' class='withicon group-btn lefticon "+icons[icons.length-1]+"'></a>");
				//<a onclick='showTabDorpdownMenuty(this)' href='####' class='withicon group-btn righticon fa fa-caret-down'></a>
				sb.append("<ul class='awe-group-dropdown'>");
				for (int i=0;i<funs.length;i++) {
					sb.append("<li><a onclick='"+funs[i]+"("+arg+")' href='####' title='"+titles[i]+"' class='withmenu group-btn "+icons[i]+"'></a></li>");
				}
				sb.append("</ul></div>");
			}else{
				sb.append("<a onclick=\""+menu.getFun()+"("+arg+")\" href=\"####\" class=\"btn-menu "+menu.getIcon()+" menu-more\" title=\""+menu.getTitle()+"\"></a>");
			}
		}
	}

	/**
	 * åŠ è½½ç»„å’Œè?œå?•js
	 * @param sb
	 */
	private void loadGroupJs(StringBuffer sb){
		//sb.append("<script>function showTabDorpdownMenuty(obj){$(obj).parent('.awesome-group').addClass('dropdown');}$('.awesome-group .righticon').hover(function(){$(this).parent('.awesome-group').addClass('dropdown');");
		sb.append("<script>$('.awesome-group .lefticon').hover(function(){$(this).parent('.awesome-group').addClass('dropdown');");
		sb.append("},function(){});$('.awesome-group').hover(function(){$(this).addClass('active');},function(){$(this).removeClass('active');$(this).removeClass('dropdown');});</script>");
	}
	/**
	 * åˆ¤æ–­è¯¥codeæ˜¯å?¦æœ‰æ?ƒé™?
	 * @param code
	 * @param optcodes
	 * @return
	 */
	public boolean hasAuth(String code,List<String> optcodes) {
		//è¯¥requestä¸­èŽ·å?–çš„code listæ˜¯é…?ç½®äº†ä½†æœªç»?æŽˆæ?ƒçš„ æ‰€ä»¥è¿™é‡Œè¦?å?–å??
		return !optcodes.contains(name+"-"+code);
    }
	
	/**
	 * èŽ·å?–æ“?ä½œæŒ‰é’®çš„çš„code
	 * @return
	 */
	private List<String> getOperationcodes(){
		//æ?ƒé™?åˆ¤æ–­
		List<String> list = new ArrayList<String>();
		Set<String> operationCodeIds = (Set<String>) super.pageContext.getRequest().getAttribute(Globals.OPERATIONCODES);
		systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		if (null!=operationCodeIds) {
			for (String operationCodeId : operationCodeIds) {
				if (oConvertUtils.isEmpty(operationCodeId))
					continue;
				TSOperation operation = systemService.getEntity(TSOperation.class, operationCodeId);
				if(operation!=null){
					list.add(operation.getOperationcode());
				}
			}
		}
		return list;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the codes
	 */
	public String getCodes() {
		return codes;
	}

	/**
	 * @param codes the codes to set
	 */
	public void setCodes(String codes) {
		this.codes = codes;
	}

	/**
	 * @return the notIn
	 */
	public boolean isNotIn() {
		return notIn;
	}

	/**
	 * @param notIn the notIn to set
	 */
	public void setNotIn(boolean notIn) {
		this.notIn = notIn;
	}

	/**
	 * @return the mm
	 */
	public boolean isMm() {
		return mm;
	}

	/**
	 * @param mm the mm to set
	 */
	public void setMm(boolean mm) {
		this.mm = mm;
	}

	/**
	 * @return the group
	 */
	public boolean isGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(boolean group) {
		this.group = group;
	}
	
	/**
	 * @return the superQuery
	 */
	public boolean isSuperQuery() {
		return superQuery;
	}

	/**
	 * @param superQuery the superQuery to set
	 */
	public void setSuperQuery(boolean superQuery) {
		this.superQuery = superQuery;
	}

	
	/**
	 * é«˜çº§æŸ¥è¯¢æž„é€ å™¨
	 * @param sb
	 */
	private void addAdvancedQuery(StringBuffer sb,String queryCode,String tableName) {
		MenuButtonsEnum menu = MenuButtonsEnum.getMenuByCode("superQuery");
		sb.append("<a title=\""+menu.getTitle()+"\" onclick=\"superQuery()\" href=\"####\" class=\"btn-menu "+menu.getIcon()+" menu-more\" ></a>");
		//onclick=\"superQuery('"+codes+"','"+tableName+"')\"
		/*FreemarkerHelper free = new FreemarkerHelper();
		Map<String, Object> mainConfig = new HashMap<String, Object>();
		mainConfig.put("queryCode", queryCode);
		mainConfig.put("tableName", tableName);
		String complexSuperQuery = free.parseTemplate("/org/jeecgframework/tag/ftl/complexSuperQueryForPage.ftl", mainConfig);
		appendLine(sb,complexSuperQuery);*/
	}
	private void appendLine(StringBuffer sb,String str) {
		String format = "\r\n"; //è°ƒè¯•  æ ¼å¼?åŒ–
		sb.append(str).append(format);
	}
}
