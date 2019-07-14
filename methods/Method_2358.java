/** 
 * ??request????
 * @param request
 * @param paramName
 * @return
 */
public String removeParam(HttpServletRequest request,String paramName){
  String queryString="";
  Enumeration keys=request.getParameterNames();
  while (keys.hasMoreElements()) {
    String key=(String)keys.nextElement();
    if (key.equals(paramName)) {
      continue;
    }
    if ("".equals(queryString)) {
      queryString=key + "=" + request.getParameter(key);
    }
 else {
      queryString+="&" + key + "=" + request.getParameter(key);
    }
  }
  return queryString;
}
