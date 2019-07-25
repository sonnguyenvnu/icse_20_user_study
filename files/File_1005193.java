package org.jeecgframework.tag.core.easyui;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;

import org.jeecgframework.tag.core.JeecgTag;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.SysThemesUtil;


/**
 * 
 * ç±»æ??è¿°ï¼šè?œå?•æ ‡ç­¾
 * 
 * å¼ ä»£æµ©
 * @dateï¼š æ—¥æœŸï¼š2012-12-7 æ—¶é—´ï¼šä¸Šå?ˆ10:17:45
 * @version 1.0
 */
public class MenuTag extends JeecgTag {
	private static final long serialVersionUID = 1L;
	protected String style="easyui";//è?œå?•æ ·å¼?
	protected List<TSFunction> parentFun;//ä¸€çº§è?œå?•
	protected List<TSFunction> childFun;//äºŒçº§è?œå?•
	protected Map<Integer, List<TSFunction>> menuFun;//è?œå?•Map
	
	
	public void setParentFun(List<TSFunction> parentFun) {
		this.parentFun = parentFun;
	}

	public void setChildFun(List<TSFunction> childFun) {
		this.childFun = childFun;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		JspWriter out = null;
		StringBuffer endString = null;
		try {

			out = this.pageContext.getOut();
			endString = end();
			out.print(endString.toString());
			out.flush();
//			String menu = (String) this.pageContext.getSession().getAttribute("leftMenuCache"+style);
//			if(menu!=null){
//				out.print(menu);
//				out.flush();
//			}else{
//				menu=end().toString();
//				this.pageContext.getSession().setAttribute("leftMenuCache"+style,menu);
//				out.print(menu);
//				out.flush();
//			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.clearBuffer();
				//æ·»åŠ ç¼“å­˜å?Žï¼Œä¸?éœ€è¦?æ¸…ç©ºï¼Œå¹¶ä¸”æ¸…ç©ºå?Žä¼šå¯¹ç¼“å­˜é€ æˆ?å½±å“?
//				if(endString != null){
//					endString.setLength(0);
//				}
//				endString = null;
				//end().setLength(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return EVAL_PAGE;
	}

	public StringBuffer end() {	

		StringBuffer sb = this.getTagCache();
		if(sb != null){
			return sb;
		}
		sb = new StringBuffer();

        if (style.equals("easyui")) {
            sb.append("<ul id=\"nav\" class=\"easyui-tree tree-lines\" fit=\"true\" border=\"false\">");
            sb.append(ListtoMenu.getEasyuiMultistageTree(menuFun, style));
            sb.append("</ul>");
        }
		if(style.equals("shortcut"))

//		{	sb.append("<div id=\"nav\" style=\"display:none;\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">");
		{
            sb.append("<div id=\"nav\" style=\"display:block;\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">");

			sb.append(ListtoMenu.getEasyuiMultistageTree(menuFun, style));
			sb.append("</div>");
		}

		if(style.equals("bootstrap"))
		{
			sb.append(ListtoMenu.getBootMenu(parentFun, childFun));
		}
		if(style.equals("json"))
		{
			sb.append("<script type=\"text/javascript\">");
			sb.append("var _menus="+ListtoMenu.getMenu(parentFun, childFun));
			sb.append("</script>");
		}
		if(style.equals("june_bootstrap"))
		{
			sb.append(ListtoMenu.getBootstrapMenu(menuFun));
		}
		if(style.equals("ace"))
		{
			sb.append(ListtoMenu.getAceMultistageTree(menuFun));
		}
		if(style.equals("diy"))
		{
			sb.append(ListtoMenu.getDIYMultistageTree(menuFun));
		}
		if(style.equals("hplus")){
			sb.append(ListtoMenu.getHplusMultistageTree(menuFun));
		}else if (style.equals("fineui")){
			sb.append(ListtoMenu.getFineuiMultistageTree(menuFun));
		}
		if(style.equals("adminlte")) {
			sb.append(ListtoMenu.getAdminlteTree(menuFun));
		}

		this.putTagCache(sb);

		return sb;
	}
	public void setStyle(String style) {
		this.style = style;
	}

	public void setMenuFun(Map<Integer, List<TSFunction>> menuFun) {
		this.menuFun = menuFun;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuTag [style=").append(style);
		builder.append(",sessionid=").append(ContextHolderUtils.getSession().getId());
		builder.append(",sysTheme=").append(SysThemesUtil.getSysTheme(ContextHolderUtils.getRequest()).getStyle())
				.append(",brower_type=").append(ContextHolderUtils.getSession().getAttribute("brower_type"))
				.append("]");
		return builder.toString();
	}


	

}
