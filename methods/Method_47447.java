/** 
 * ?request?????Map???????Map.
 * @param request the request
 * @return the parameter map
 */
public static Map<String,Object> getParameterMap(HttpServletRequest request){
  Map<String,String[]> properties=request.getParameterMap();
  Map<String,Object> returnMap=new HashMap<String,Object>();
  Set<String> keySet=properties.keySet();
  for (  String key : keySet) {
    String[] values=properties.get(key);
    String value="";
    if (values != null && (values.length == 1 && StringUtils.isNotBlank(values[0])) ? true : false) {
      for (int i=0; i < values.length; i++) {
        if (values[i] != null && !"".equals(values[i])) {
          value+=values[i] + ",";
        }
      }
      if (value != null && !"".equals(value)) {
        value=value.substring(0,value.length() - 1);
      }
      if (key.equals("keywords")) {
        value=value.replace("_","\\_").replace("%","\\%");
      }
      returnMap.put(key,value);
    }
  }
  return returnMap;
}
