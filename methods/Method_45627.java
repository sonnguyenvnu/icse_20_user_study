/** 
 * ???????????
 * @param methodName   ???
 * @param paramKey     ?????
 * @param defaultValue ???
 * @return ?????false boolean method param
 */
protected boolean getBooleanMethodParam(String methodName,String paramKey,boolean defaultValue){
  if (CommonUtils.isEmpty(configContext)) {
    return defaultValue;
  }
  Boolean o=(Boolean)configContext.get(buildMethodKey(methodName,paramKey));
  if (o == null) {
    o=(Boolean)configContext.get(paramKey);
    return o == null ? defaultValue : o;
  }
 else {
    return o;
  }
}
