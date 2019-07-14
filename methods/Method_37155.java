private boolean isValidTraceMethodName(String methodName){
  return !methodName.equals(ON_TRACE_METHOD_NAME) && methodName.startsWith(ON_TRACE_METHOD_NAME) || (methodName.startsWith(ON_TRACE_METHOD_PREFIX) && methodName.endsWith(ON_TRACE_METHOD_POSTFIX));
}
