package org.jeecgframework.tag.core.easyui;

import java.util.Map;

/**
 * 
 * @author  张代浩
 *
 */
public class PagerUtil {
	private int curPageNO = 1; // 当�?页
	private int rowsCount; // 记录行数
	private int pageCount; // 页数
	private String actionUrl;// 目标ACTION
	private Map<String, Object> map;// �?装查询�?�件
	public PagerUtil(int curPageNo, int allCount, int pageSize, Map<String, Object> map,String actionUrl) {
		this.curPageNO = curPageNo;
		this.rowsCount = allCount;
		this.map = map;
		this.actionUrl=actionUrl;
		this.pageCount = (int) Math.ceil((double) allCount / pageSize);	
	}
	// 第一页
	public int first() {
		return 1;
	}

	// 最�?�一页
	public int last() {
		return pageCount;
	}

	// 上一页
	public int previous() {
		return (curPageNO - 1 < 1) ? 1 : curPageNO - 1;
	}

	// 下一页
	public int next() {
		return (curPageNO + 1 > pageCount) ? pageCount : curPageNO + 1;
	}

	// 第一页
	public boolean isFirst() {
		return (curPageNO == 1) ? true : false;
	}

	// 最�?�一页
	public boolean isLast() {
		return (curPageNO == pageCount) ? true : false;
	}
	protected StringBuffer getStrByImage(StringBuffer sb) {
		String join = getJoin();
		String conditions = getConditions();
		sb.append("<script language='javascript'>" + "\n");
		sb.append("function commonSubmit(val){" + "\n");
		// 校验是�?�全由数字组�?
		sb.append("var patrn=/^[0-9]{1,20}$/;" + "\n");
		sb.append("if (!patrn.exec(val)){" + "\n");
		sb.append(" alert(\"请输入有效页�?��?\");" + "\n");
		sb.append(" return false ;" + "\n");
		sb.append(" }else{" + "\n");
		sb.append("    document.getElementById('pageGoto').href='" + actionUrl + join + "curPageNO='+val+'" + conditions + "';" + "\n");
		sb.append("    return true ;" + "\n");
		sb.append("} " + "\n");
		sb.append(" }" + "\n");
		sb.append("</script>" + "\n");
		sb.append("&nbsp;<span class=pageArea id=pageArea>共<b>" + rowsCount + "</b>�?�&nbsp;当�?第" + curPageNO + "/" + pageCount + "页&nbsp;&nbsp;&nbsp;");
		if (isFirst())
			sb.append("<a class=\"pageFirstDisable\"  title=\"首页\" onMouseMove=\"style.cursor='hand'\">&nbsp;</a><a class=\"pagePreviousDisable\" title=\"上一页\"  onMouseMove=\"style.cursor='hand'\">&nbsp;</a>");
		else {
			sb.append("<a href='" + actionUrl + join + "curPageNO=1" + conditions + "' class=pageFirst title=首页 onMouseMove=\"style.cursor='hand'\"></a>");
			sb.append("<a class=\"pagePrevious\" href='" + actionUrl + join + "curPageNO=" + previous() + conditions + "' title=\"上一页\"  onMouseMove=\"style.cursor='hand'\")\">&nbsp;</a>");
		}
		if (curPageNO - pageCount == 0 || pageCount == 0 || pageCount == 1)
			sb.append("<a class=pageNextDisable  title=下一页 onMouseMove=\"style.cursor='hand'\">&nbsp;<a class=pageLastDisable title=尾页 onMouseMove=\"style.cursor='hand'\">&nbsp;</a>&nbsp;");
		else {
			sb.append("<a class=pageNext href='" + actionUrl + join + "curPageNO=" + next() + conditions + "' title=下一页 onMouseMove=\"style.cursor='hand'\")\">&nbsp;</a>");
			sb.append("<a class=pageLast href='" + actionUrl + join + "curPageNO=" + pageCount + conditions + "' title=尾页 onMouseMove=\"style.cursor='hand'\" )\">&nbsp;</a>");
		}

		if (pageCount == 1 || pageCount == 0) {
			sb.append(" &nbsp;转到:<input class=\"SmallInput\" type=text style=TEXT-ALIGN: center maxLength=4 name=\"pageroffsetll\" size=2 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;");
			sb.append("<A class=pageGoto id=pageGoto title=转到 onclick='return commonSubmit()'>&nbsp;</A>&nbsp;&nbsp;</span>");
		} else {
			sb.append(" &nbsp;转到:<input class=SmallInput type=text style=TEXT-ALIGN: center maxLength=4 name=\"pageroffsetll\" size=2 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;");
			sb.append("<A  class=pageGoto id=pageGoto title=转到 onclick='commonSubmit(document.all.pageroffsetll.value)'>&nbsp;</A>&nbsp;</span");
		}
		return sb;
	}

	protected StringBuffer getStr(StringBuffer sb) {
		String join = getJoin();
		String conditions = getConditions();

		String str = "";
		str += "";
		if (isFirst())
			sb.append("第" + curPageNO + "页&nbsp;共" + pageCount + "页&nbsp;首页 ");
		else {
			sb.append("第" + curPageNO + "页&nbsp;共" + pageCount + "页&nbsp;<a href='" + actionUrl + join + "curPageNO=1" + conditions + "'>首页</a>&nbsp;");
			sb.append("<a href='" + actionUrl + join + "curPageNO=" + previous() + conditions + "' onMouseMove=\"style.cursor='hand'\" alt=\"上一页\">上一页</a>&nbsp;");
		}
		if (isLast() || rowsCount == 0)
			sb.append("尾页&nbsp;");
		else {
			sb.append("<a href='" + actionUrl + join + "curPageNO=" + next() + conditions + "' onMouseMove=\"style.cursor='hand'\" >下一页</a>&nbsp;");
			sb.append("<a href='" + actionUrl + join + "curPageNO=" + pageCount + conditions + "'>尾页</a>&nbsp;");
		}
		sb.append("&nbsp;共" + rowsCount + "�?�记录&nbsp;");

		str += "&nbsp;转到<select name='page' onChange=\"location='" + actionUrl + join + "curPageNO='+this.options[this.selectedIndex].value\">";
		int begin = (curPageNO > 10) ? curPageNO - 10 : 1;
		int end = (pageCount - curPageNO > 10) ? curPageNO + 10 : pageCount;
		for (int i = begin; i <= end; i++) {
			if (i == curPageNO)
				str += "<option value='" + i + "' selected>第" + i + "页</option>";
			else
				str += "<option value='" + i + "'>第" + i + "页</option>";
		}
		str += "</select>";
		sb.append(str);
		return sb;
	}
/**
  * <b>Summary: </b>
  *     getConditions(拼接组�?�查询�?�件)
  * @return
 */
	protected String getConditions() {
		String conditions = "";
		if (map != null) {

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				conditions += "&" + entry.getKey() + "=" + entry.getValue();
			}
		}
		return conditions;

	}
/**
 * 
  * <b>Summary: </b>
  *     getJoin(判断连接符)
  * @return
 */
	protected String getJoin() {
		String join = "";
		if (actionUrl.indexOf("?") == -1) {
			join = "?";
		} else {
			join = "&";
		}
		return join;

	}

}
