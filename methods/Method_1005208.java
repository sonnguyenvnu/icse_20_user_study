public StringBuffer end(){
  StringBuffer sb=this.getTagCache();
  if (sb != null) {
    return sb;
  }
  sb=new StringBuffer();
  FreemarkerHelper free=new FreemarkerHelper();
  Map<String,Object> mainConfig=new HashMap<String,Object>();
  mainConfig.put("obj",this);
  mainConfig.put("style","uploadify");
  List<String> idList=(List<String>)pageContext.getRequest().getAttribute("nameList");
  mainConfig.put("idList",idList);
  String superQuery=free.parseTemplate("/org/jeecgframework/tag/ftl/plupload.ftl",mainConfig);
  String format="\r\n";
  sb.append(superQuery).append(format);
  return sb;
}
