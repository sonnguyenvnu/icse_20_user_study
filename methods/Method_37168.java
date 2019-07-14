private boolean isValidMethodName(String methodName){
  return !methodName.equals(ON_CLICK_METHOD_NAME) && methodName.startsWith(ON_CLICK_METHOD_NAME) || (methodName.startsWith(ON_CLICK_METHOD_PREFIX) && methodName.endsWith(ON_CLICK_METHOD_POSTFIX));
}
