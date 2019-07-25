public StringBuffer end(){
  StringBuffer sb=new StringBuffer();
  if (StringUtils.isBlank(selectedNamesInputId)) {
    selectedNamesInputId="userNames";
  }
  if (StringUtils.isBlank(title)) {
    title="????";
  }
  if (StringUtils.isBlank(inputWidth)) {
    inputWidth="150px";
  }
  if (StringUtils.isBlank(windowWidth)) {
    windowWidth="400px";
  }
  if (StringUtils.isBlank(windowHeight)) {
    windowHeight="350px";
  }
  if (hasLabel && oConvertUtils.isNotEmpty(title)) {
    sb.append(title + "?");
  }
  sb.append("<input class=\"inuptxt\" readonly=\"" + readonly + "\" type=\"text\" id=\"" + selectedNamesInputId + "\" name=\"" + selectedNamesInputId + "\" style=\"width: " + inputWidth + "\" onclick=\"openUserSelect()\" ");
  if (StringUtils.isNotBlank(userNamesDefalutVal)) {
    sb.append(" value=\"" + userNamesDefalutVal + "\"");
  }
  sb.append(" />");
  if (oConvertUtils.isNotEmpty(selectedIdsInputId)) {
    sb.append("<input class=\"inuptxt\" id=\"" + selectedIdsInputId + "\" name=\"" + selectedIdsInputId + "\" type=\"hidden\" ");
    if (StringUtils.isNotBlank(userIdsDefalutVal)) {
      sb.append(" value=\"" + userIdsDefalutVal + "\"");
    }
    sb.append("/>");
  }
  String commonConfirm=MutiLangUtil.getLang("common.confirm");
  String commonCancel=MutiLangUtil.getLang("common.cancel");
  sb.append("<script type=\"text/javascript\">");
  sb.append("function openUserSelect() {");
  sb.append("    $.dialog({content: 'url:userController.do?userSelect', zIndex: getzIndex(), title: '" + title + "', lock: true, width: '" + windowWidth + "', height: '" + windowHeight + "', opacity: 0.4, button: [");
  sb.append("       {name: '" + commonConfirm + "', callback: " + getCallback() + ", focus: true},");
  sb.append("       {name: '" + commonCancel + "', callback: function (){}}");
  sb.append("   ]});");
  sb.append("}");
  sb.append("function callbackUserSelect() {");
  sb.append("var iframe = this.iframe.contentWindow;");
  sb.append("var rowsData = iframe.$('#userList1').datagrid('getSelections');");
  sb.append("if (!rowsData || rowsData.length==0) {");
  sb.append("tip('<t:mutiLang langKey=\"common.please.select.edit.item\"/>');");
  sb.append("return;");
  sb.append("}");
  sb.append(" var ids='',names='';");
  sb.append("for(i=0;i<rowsData.length;i++){");
  sb.append(" var node = rowsData[i];");
  sb.append(" ids += node.id+',';");
  sb.append(" names += node.realName+',';");
  sb.append("}");
  sb.append("$('#" + selectedNamesInputId + "').val(names);");
  sb.append("$('#" + selectedNamesInputId + "').blur();");
  if (oConvertUtils.isNotEmpty(selectedIdsInputId)) {
    sb.append("$('#" + selectedIdsInputId + "').val(ids);");
  }
  sb.append("}");
  sb.append("</script>");
  return sb;
}
