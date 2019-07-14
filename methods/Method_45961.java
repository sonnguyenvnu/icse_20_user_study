/** 
 * ???????????????????
 * @param itfClass ???
 */
protected void checkMethods(Class<?> itfClass){
  ConcurrentHashMap<String,Boolean> methodsLimit=new ConcurrentHashMap<String,Boolean>();
  for (  Method method : itfClass.getMethods()) {
    String methodName=method.getName();
    if (methodsLimit.containsKey(methodName)) {
      if (LOGGER.isWarnEnabled(providerConfig.getAppName())) {
        LOGGER.warnWithApp(providerConfig.getAppName(),"Method with same name \"" + itfClass.getName() + "." + methodName + "\" exists ! The usage of overloading method in rpc is deprecated.");
      }
    }
    Boolean include=methodsLimit.get(methodName);
    if (include == null) {
      include=inList(providerConfig.getInclude(),providerConfig.getExclude(),methodName);
      methodsLimit.putIfAbsent(methodName,include);
    }
  }
  providerConfig.setMethodsLimit(methodsLimit);
}
