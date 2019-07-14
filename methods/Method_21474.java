private boolean getBooleanOrDefault(Map<String,String> params,String param,boolean defaultValue){
  boolean flat=defaultValue;
  if (params.containsKey(param)) {
    flat=Boolean.parseBoolean(params.get(param));
  }
  return flat;
}
