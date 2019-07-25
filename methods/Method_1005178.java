public StringBuffer end(){
  String confirm=MutiLangUtil.getLang("common.confirm");
  String cancel=MutiLangUtil.getLang("common.cancel");
  String methodname=UUIDGenerator.generate().replaceAll("-","");
  StringBuffer sb=new StringBuffer();
  sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + icon + "\" onClick=\"choose_" + methodname + StringUtil.replace("()\">{0}</a>","{0}",MutiLangUtil.getLang("common.select",langArg)));
  if (isclear && StringUtil.isNotEmpty(textname)) {
    sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-redo\" onClick=\"clearAll_" + methodname + StringUtil.replace("();\">{0}</a>","{0}",MutiLangUtil.getLang("common.clear",langArg)));
  }
  sb.append("<script type=\"text/javascript\">");
  sb.append("var windowapi;");
  sb.append("try{");
  sb.append("windowapi = frameElement.api, W = windowapi.opener;");
  sb.append("}catch(e){}");
  sb.append("function choose_" + methodname + "(){");
  sb.append("var url = ").append("'").append(url).append("';");
  if (isInit) {
    sb.append("var initValue = ").append("$(\'#" + hiddenName + "\').val();");
    sb.append("url += ").append("'&ids='+initValue;");
  }
  sb.append("if(typeof(windowapi) == 'undefined'){");
  sb.append("$.dialog({");
  sb.append("content: \'url:\'+url,");
  sb.append("zIndex: getzIndex(),");
  if (title != null) {
    sb.append("title: \'" + title + "\',");
  }
  sb.append("lock : true,");
  if (width != null) {
    sb.append("width :\'" + width + "\',");
  }
 else {
    sb.append("width :400,");
  }
  if (height != null) {
    sb.append("height :\'" + height + "\',");
  }
 else {
    sb.append("height :350,");
  }
  if (left != null) {
    sb.append("left :\'" + left + "\',");
  }
 else {
    sb.append("left :'85%',");
  }
  if (top != null) {
    sb.append("top :\'" + top + "\',");
  }
 else {
    sb.append("top :'65%',");
  }
  sb.append("opacity : 0.4,");
  sb.append("button : [ {");
  sb.append(StringUtil.replace("name : \'{0}\',","{0}",confirm));
  sb.append("callback : clickcallback_" + methodname + ",");
  sb.append("focus : true");
  sb.append("}, {");
  sb.append(StringUtil.replace("name : \'{0}\',","{0}",cancel));
  sb.append("callback : function() {");
  sb.append("}");
  sb.append("} ]");
  sb.append("});");
  sb.append("}else{");
  sb.append("$.dialog({");
  sb.append("content: \'url:\'+url,");
  sb.append("zIndex: getzIndex(),");
  if (title != null) {
    sb.append("title: \'" + title + "\',");
  }
  sb.append("lock : true,");
  sb.append("parent:windowapi,");
  if (width != null) {
    sb.append("width :\'" + width + "\',");
  }
 else {
    sb.append("width :400,");
  }
  if (height != null) {
    sb.append("height :\'" + height + "\',");
  }
 else {
    sb.append("height :350,");
  }
  if (left != null) {
    sb.append("left :\'" + left + "\',");
  }
 else {
    sb.append("left :'85%',");
  }
  if (top != null) {
    sb.append("top :\'" + top + "\',");
  }
 else {
    sb.append("top :'65%',");
  }
  sb.append("opacity : 0.4,");
  sb.append("button : [ {");
  sb.append(StringUtil.replace("name : \'{0}\',","{0}",confirm));
  sb.append("callback : clickcallback_" + methodname + ",");
  sb.append("focus : true");
  sb.append("}, {");
  sb.append(StringUtil.replace("name : \'{0}\',","{0}",cancel));
  sb.append("callback : function() {");
  sb.append("}");
  sb.append("} ]");
  sb.append("});");
  sb.append("}");
  sb.append("}");
  clearAll(sb,methodname);
  callback(sb,methodname);
  sb.append("</script>");
  return sb;
}
