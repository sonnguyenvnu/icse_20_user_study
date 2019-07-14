/** 
 * Returns whether the given method is an attribute accessor, in which case a corresponding Attribute will be added to the iterator.
 * @param method The method to test
 */
protected boolean isAttributeAccessor(Method method){
  String methodName=method.getName();
  return isConsideredReturnType(method.getReturnType()) && method.getParameterTypes().length == 0 && !methodName.startsWith("jjt") && !FILTERED_OUT_NAMES.contains(methodName);
}
