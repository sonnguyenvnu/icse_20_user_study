private Method getMatchedFeignContractMethod(Class<?> targetType,List<Method> methods,String expectedConfigKey){
  Method matchedMethod=null;
  for (  Method method : methods) {
    String configKey=Feign.configKey(targetType,method);
    if (expectedConfigKey.equals(configKey)) {
      matchedMethod=method;
      break;
    }
  }
  return matchedMethod;
}
