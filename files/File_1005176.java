package org.jeecgframework.tag.core.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;


/**
 * 
 * 自动补全
 * jquery ui Autocomplete
 * @author:  张代浩
 * @date： 日期：2012-12-7 时间：上�?�10:17:45
 * @version 1.0
 */
public class AutocompleteTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String name;		//input控件name
	private String entityName;	//查询Hiber实体�??
	private String searchField;	//查询字段
	
	private String defValue;	 //默认值
	private String dataSource = "commonController.do?getAutoList";//数�?��?URL
	private Integer minLength=1; //触�?��??示文字长度
	
	private String datatype;	//数�?�验�?类型
	private String nullmsg = "";	//数�?�为空时验�?
	private String errormsg = "输入格�?�?对";	//数�?�格�?�?对时验�?
	private String parse;    	//转�?�数�?�JS方法�??
	private String formatItem; 	//格�?化�?显示的数�?�JS方法�??
	private String result; 		//选择�?�回调JS方法�??
	private Integer maxRows = 10;//显示的最多的�?�数
	
	
	public String getDefValue() {
		return defValue;
	}
	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public void setNullmsg(String nullmsg) {
		this.nullmsg = nullmsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	
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
		}
		return EVAL_PAGE;
	}
	public StringBuffer end() {
		StringBuffer nsb = new StringBuffer();
		nsb.append("<script type=\"text/javascript\">");
		nsb.append("$(document).ready(function() {") 
		.append("$(\"#"+name+"\").autocomplete(\""+dataSource+"\",{")
		.append("max: 5,minChars: "+minLength+",width: 200,scrollHeight: 100,matchContains: true,autoFill: false,extraParams:{")
        .append("featureClass : \"P\",style : \"full\",	maxRows : "+maxRows+",labelField : \""+searchField+"\",valueField : \""+searchField+"\",")
		.append("searchField : \""+searchField+"\",entityName : \""+entityName+"\",trem: getTremValue"+name+"}");
		if(StringUtil.isNotEmpty(parse)){
			nsb.append(",parse:function(data){return "+parse+".call(this,data);}");
		}else{
			nsb.append(",parse:function(data){return jeecgAutoParse.call(this,data);}");
		}
		if(StringUtil.isNotEmpty(formatItem)){
			nsb.append(",formatItem:function(row, i, max){return "+formatItem+".call(this,row, i, max);} ");
		}else{
			nsb.append(",formatItem:function(row, i, max){return row['"+searchField+"'];}");
		}
		nsb.append("}).result(function (event, row, formatted) {");
		if(StringUtil.isNotEmpty(result)){
			nsb.append(result+".call(this,row); ");
		}else{
			nsb.append("$(\"#"+name+"\").val(row['"+searchField+"']);");
		}
		nsb.append("}); });")
		.append("function getTremValue"+name+"(){return $(\"#"+name+"\").val();}")
        .append("</script>")
        .append("<input type=\"text\" id=\""+name+"\" name=\""+name+"\" ");
		if(oConvertUtils.isNotEmpty(datatype)){
			nsb.append("datatype=\""+datatype+"\" nullmsg=\""+nullmsg+"\" errormsg=\""+errormsg+"\" ");
		}
		nsb.append("/>");
		if(StringUtil.isNotEmpty(defValue)){
			nsb.append(" value="+defValue+" readonly=true");
		}
		return nsb;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setParse(String parse) {
		this.parse = parse;
	}
	public void setFormatItem(String formatItem) {
		this.formatItem = formatItem;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public void setMaxRows(Integer maxRows){
		if(maxRows==null){
			maxRows = 10;
		}
		this.maxRows = maxRows;
	}
}
