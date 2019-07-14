/** 
 * Single point of  {@link ActionDefinition} creation.Also performs the replacement of action path macros!
 */
protected ActionDefinition createActionDef(String path,String httpMethod,String resultBasePath,final ActionNames actionNames){
  path=replaceActionNameMacros(path,actionNames);
  if (httpMethod != null) {
    httpMethod=replaceActionNameMacros(httpMethod,actionNames);
  }
  if (resultBasePath != null) {
    resultBasePath=replaceActionNameMacros(resultBasePath,actionNames);
  }
  return new ActionDefinition(path,httpMethod,resultBasePath);
}
