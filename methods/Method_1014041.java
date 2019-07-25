@Modified protected void modified(Map<String,Object> config){
  if (config != null) {
    defaultServiceId=(String)config.get("default");
  }
}
