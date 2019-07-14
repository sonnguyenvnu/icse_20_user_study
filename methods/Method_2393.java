/** 
 * ??url??code?username??
 * @param request
 * @return
 */
public static String getParameterWithOutCode(HttpServletRequest request){
  StringBuffer backUrl=request.getRequestURL();
  String params="";
  Map<String,String[]> parameterMap=request.getParameterMap();
  for (  Map.Entry<String,String[]> entry : parameterMap.entrySet()) {
    if (!"upms_code".equals(entry.getKey()) && !"upms_username".equals(entry.getKey())) {
      if ("".equals(params)) {
        params=entry.getKey() + "=" + entry.getValue()[0];
      }
 else {
        params+="&" + entry.getKey() + "=" + entry.getValue()[0];
      }
    }
  }
  if (!StringUtils.isBlank(params)) {
    backUrl=backUrl.append("?").append(params);
  }
  return backUrl.toString();
}
