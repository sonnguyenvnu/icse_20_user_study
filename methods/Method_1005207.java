private void tree(StringBuffer resultSb){
  resultSb.append("<div id=\"");
  resultSb.append("show" + StringUtil.firstUpperCase(field) + "TreeContent\" ");
  if (StringUtil.isNotEmpty(divClass)) {
    resultSb.append("class=\"" + divClass + "\"  ");
  }
 else {
    resultSb.append("class=\"menuContent\"  ");
  }
  resultSb.append("  style=\"display: none; position: absolute; border: 1px #CCC solid; background-color: #F0F6E4;z-index:9999;\"> ");
  resultSb.append("<ul id=\"show" + StringUtil.firstUpperCase(field) + "Tree\" class=\"ztree\" style=\"margin-top:0;\"></ul></div>");
}
