public static void setDisableJvmFirst(ClassLoader classLoader,boolean disableJvmFirst){
  disableJvmFirstMap.putIfAbsent(classLoader,disableJvmFirst);
}
