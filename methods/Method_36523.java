public static boolean isSkipJvmReferenceHealthCheck(SofaRuntimeContext sofaRuntimeContext){
  return isSkipJvmReferenceHealthCheck(sofaRuntimeContext.getAppClassLoader());
}
