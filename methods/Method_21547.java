@Override public boolean isChildren(){
  Map<String,Object> paramsAsMap=this.getParamsAsMap();
  return paramsAsMap.containsKey("children");
}
