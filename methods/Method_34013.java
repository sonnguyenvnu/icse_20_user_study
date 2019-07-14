public Map<String,String> parseParameters(HttpServletRequest request){
  Map<String,String> parameters=parseHeaderParameters(request);
  if (parameters == null) {
    parameters=new HashMap<String,String>();
    for (    String supportedOAuthParameter : getSupportedOAuthParameters()) {
      String param=request.getParameter(supportedOAuthParameter);
      if (param != null) {
        parameters.put(supportedOAuthParameter,param);
      }
    }
  }
  return parameters;
}
