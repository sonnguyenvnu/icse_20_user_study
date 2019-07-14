public Map<String,Object> getConfig(int type){
  Map<String,Object> conf=new HashMap<String,Object>();
  String savePath=null;
switch (type) {
case ActionMap.UPLOAD_FILE:
    conf.put("isBase64","false");
  conf.put("maxSize",this.jsonConfig.getLong("fileMaxSize"));
conf.put("allowFiles",this.getArray("fileAllowFiles"));
conf.put("fieldName",this.jsonConfig.getString("fileFieldName"));
savePath=this.jsonConfig.getString("filePathFormat");
break;
case ActionMap.UPLOAD_IMAGE:
conf.put("isBase64","false");
conf.put("maxSize",this.jsonConfig.getLong("imageMaxSize"));
conf.put("allowFiles",this.getArray("imageAllowFiles"));
conf.put("fieldName",this.jsonConfig.getString("imageFieldName"));
savePath=this.jsonConfig.getString("imagePathFormat");
break;
case ActionMap.UPLOAD_VIDEO:
conf.put("maxSize",this.jsonConfig.getLong("videoMaxSize"));
conf.put("allowFiles",this.getArray("videoAllowFiles"));
conf.put("fieldName",this.jsonConfig.getString("videoFieldName"));
savePath=this.jsonConfig.getString("videoPathFormat");
break;
case ActionMap.UPLOAD_SCRAWL:
conf.put("filename",ConfigManager.SCRAWL_FILE_NAME);
conf.put("maxSize",this.jsonConfig.getLong("scrawlMaxSize"));
conf.put("fieldName",this.jsonConfig.getString("scrawlFieldName"));
conf.put("isBase64","true");
savePath=this.jsonConfig.getString("scrawlPathFormat");
break;
case ActionMap.CATCH_IMAGE:
conf.put("filename",ConfigManager.REMOTE_FILE_NAME);
conf.put("filter",this.getArray("catcherLocalDomain"));
conf.put("maxSize",this.jsonConfig.getLong("catcherMaxSize"));
conf.put("allowFiles",this.getArray("catcherAllowFiles"));
conf.put("fieldName",this.jsonConfig.getString("catcherFieldName") + "[]");
savePath=this.jsonConfig.getString("catcherPathFormat");
break;
case ActionMap.LIST_IMAGE:
conf.put("allowFiles",this.getArray("imageManagerAllowFiles"));
conf.put("dir",this.jsonConfig.getString("imageManagerListPath"));
conf.put("count",this.jsonConfig.getIntValue("imageManagerListSize"));
break;
case ActionMap.LIST_FILE:
conf.put("allowFiles",this.getArray("fileManagerAllowFiles"));
conf.put("dir",this.jsonConfig.getString("fileManagerListPath"));
conf.put("count",this.jsonConfig.getIntValue("fileManagerListSize"));
break;
}
conf.put("savePath",savePath);
conf.put("rootPath",this.rootPath);
return conf;
}
