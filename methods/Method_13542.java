private Map<String,List<String>> getParams(HttpServletRequest request){
  Map<String,List<String>> map=new LinkedHashMap<>();
  for (  Map.Entry<String,String[]> entry : request.getParameterMap().entrySet()) {
    map.put(entry.getKey(),Arrays.asList(entry.getValue()));
  }
  return map;
}
