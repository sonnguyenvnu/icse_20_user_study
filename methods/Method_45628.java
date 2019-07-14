/** 
 * ???????????
 * @param methodName   ???
 * @param paramKey     ?????
 * @param defaultValue ???
 * @return ?????null string method param
 */
protected String getStringMethodParam(String methodName,String paramKey,String defaultValue){
  if (CommonUtils.isEmpty(configContext)) {
    return defaultValue;
  }
  String o=(String)configContext.get(buildMethodKey(methodName,paramKey));
  if (o == null) {
    o=(String)configContext.get(paramKey);
    return o == null ? defaultValue : o;
  }
 else {
    return o;
  }
}
