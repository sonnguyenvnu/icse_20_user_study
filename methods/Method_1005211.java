public void end(StringBuffer sb){
  FreemarkerHelper free=new FreemarkerHelper();
  Map<String,Object> mainConfig=new HashMap<String,Object>();
  mainConfig.put("obj",this);
  String superQuery=free.parseTemplate("/org/jeecgframework/tag/ftl/plupload.ftl",mainConfig);
  String format="\r\n";
  sb.append(superQuery).append(format);
}
