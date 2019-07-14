@Override public boolean isNested(){
  Map<String,Object> paramsAsMap=this.getParamsAsMap();
  return paramsAsMap.containsKey("nested") || paramsAsMap.containsKey("reverse_nested");
}
