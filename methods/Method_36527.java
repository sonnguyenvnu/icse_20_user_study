public static void unRegisterProperties(ClassLoader classLoader){
  skipJvmReferenceHealthCheckMap.remove(classLoader);
  disableJvmFirstMap.remove(classLoader);
}
