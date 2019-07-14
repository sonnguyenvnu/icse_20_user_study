private Map<String,String> getSingleValueMap(HttpServletRequest request){
  Map<String,String> map=new HashMap<String,String>();
  Map<String,String[]> parameters=request.getParameterMap();
  for (  String key : parameters.keySet()) {
    String[] values=parameters.get(key);
    map.put(key,values != null && values.length > 0 ? values[0] : null);
  }
  return map;
}
