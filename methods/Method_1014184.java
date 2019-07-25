@Modified protected void modified(Map<String,Object> config){
  Object iconSetId=config.get("default");
  if (iconSetId instanceof String) {
    defaultIconSetId=(String)iconSetId;
  }
}
