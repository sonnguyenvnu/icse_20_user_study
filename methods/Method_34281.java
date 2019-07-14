@Override protected RequestMappingInfo getMappingForMethod(Method method,Class<?> handlerType){
  RequestMappingInfo defaultMapping=super.getMappingForMethod(method,handlerType);
  if (defaultMapping == null) {
    return null;
  }
  Set<String> defaultPatterns=defaultMapping.getPatternsCondition().getPatterns();
  String[] patterns=new String[defaultPatterns.size()];
  int i=0;
  for (  String pattern : defaultPatterns) {
    patterns[i]=getPath(pattern);
    paths.add(pattern);
    i++;
  }
  PatternsRequestCondition patternsInfo=new PatternsRequestCondition(patterns,getUrlPathHelper(),getPathMatcher(),useSuffixPatternMatch(),useTrailingSlashMatch(),getFileExtensions());
  ParamsRequestCondition paramsInfo=defaultMapping.getParamsCondition();
  if (!approvalParameter.equals(OAuth2Utils.USER_OAUTH_APPROVAL) && defaultPatterns.contains("/oauth/authorize")) {
    String[] params=new String[paramsInfo.getExpressions().size()];
    Set<NameValueExpression<String>> expressions=paramsInfo.getExpressions();
    i=0;
    for (    NameValueExpression<String> expression : expressions) {
      String param=expression.toString();
      if (OAuth2Utils.USER_OAUTH_APPROVAL.equals(param)) {
        params[i]=approvalParameter;
      }
 else {
        params[i]=param;
      }
      i++;
    }
    paramsInfo=new ParamsRequestCondition(params);
  }
  RequestMappingInfo mapping=new RequestMappingInfo(patternsInfo,defaultMapping.getMethodsCondition(),paramsInfo,defaultMapping.getHeadersCondition(),defaultMapping.getConsumesCondition(),defaultMapping.getProducesCondition(),defaultMapping.getCustomCondition());
  return mapping;
}
