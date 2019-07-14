public static boolean isDisableJvmFirst(SofaRuntimeContext sofaRuntimeContext){
  return isDisableJvmFirst(sofaRuntimeContext.getAppClassLoader());
}
