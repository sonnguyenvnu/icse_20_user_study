/** 
 * ??????Map<String, String>,for???????,????????request.getParameterMap(),??Map<String, String[]>
 * @param request
 * @return
 */
public static Map<String,String> getParameterMap(HttpServletRequest request){
  Map<String,String> result=new HashMap<>();
  Enumeration parameterNames=request.getParameterNames();
  while (parameterNames.hasMoreElements()) {
    String parameterName=(String)parameterNames.nextElement();
    result.put(parameterName,request.getParameter(parameterName));
  }
  return result;
}
