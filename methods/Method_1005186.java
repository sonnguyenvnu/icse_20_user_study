public StringBuffer end(){
  StringBuffer sb=new StringBuffer();
  if (StringUtils.isBlank(selectedNamesInputId)) {
    selectedNamesInputId="orgNames";
  }
  if (StringUtils.isBlank(selectedIdsInputId)) {
    selectedIdsInputId="orgIds";
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
  sb.append("<input class=\"inuptxt\" readonly=\"true\" type=\"text\" id=\"" + selectedNamesInputId + "\" name=\"" + selectedNamesInputId + "\" style=\"width: " + inputWidth + "\" onclick=\"openDepartmentSelect()\" ");
  if (StringUtils.isNotBlank(departNamesDefalutVal)) {
    sb.append(" value=\"" + departNamesDefalutVal + "\"");
  }
  sb.append(" />");
  String orgIds="";
  sb.append("<input class=\"inuptxt\" id=\"" + selectedIdsInputId + "\" name=\"" + selectedIdsInputId + "\" type=\"hidden\" ");
  if (StringUtils.isNotBlank(departIdsDefalutVal)) {
    sb.append(" value=\"" + departIdsDefalutVal + "\"");
    orgIds="&orgIds=" + departIdsDefalutVal;
  }
  sb.append("/>");
  String commonDepartmentList=MutiLangUtil.getLang("common.department.list");
  String commonConfirm=MutiLangUtil.getLang("common.confirm");
  String commonCancel=MutiLangUtil.getLang("common.cancel");
  sb.append("<script type=\"text/javascript\">");
  sb.append("function openDepartmentSelect() {");
  sb.append("    $.dialog.setting.zIndex = 9999; ");
  sb.append("    $.dialog({content: 'url:departController.do?departSelect" + orgIds + "', zIndex: 2100, title: '" + commonDepartmentList + "', lock: true, width: '" + windowWidth + "', height: '" + windowHeight + "', opacity: 0.4, button: [");
  sb.append("       {name: '" + commonConfirm + "', callback: callbackDepartmentSelect, focus: true},");
  sb.append("       {name: '" + commonCancel + "', callback: function (){}}");
  sb.append("   ]}).zindex();");
  sb.append("}");
  sb.append("function callbackDepartmentSelect() {");
  sb.append("    var iframe = this.iframe.contentWindow;");
  sb.append(" var treeObj = iframe.$.fn.zTree.getZTreeObj(\"departSelect\");");
  sb.append(" var nodes = treeObj.getCheckedNodes(true);");
  sb.append(" if(nodes.length>0){");
  sb.append(" var ids='',names='';");
  sb.append("for(i=0;i<nodes.length;i++){");
  sb.append(" var node = nodes[i];");
  sb.append(" ids += node.id+',';");
  sb.append(" names += node.name+',';");
  sb.append("}");
  sb.append(" $('#" + selectedNamesInputId + "').val(names);");
  sb.append(" $('#" + selectedNamesInputId + "').blur();");
  sb.append(" $('#" + selectedIdsInputId + "').val(ids);");
  sb.append("}");
  sb.append("}");
  sb.append("</script>");
  return sb;
}
