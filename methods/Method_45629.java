/** 
 * ???????????
 * @param methodName ???
 * @param paramKey   ?????
 * @return ?????null method param
 */
protected Object getMethodParam(String methodName,String paramKey){
  if (CommonUtils.isEmpty(configContext)) {
    return null;
  }
  Object o=configContext.get(buildMethodKey(methodName,paramKey));
  return o == null ? configContext.get(paramKey) : o;
}
