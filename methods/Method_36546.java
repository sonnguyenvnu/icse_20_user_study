public static void registerAsyncInitBean(String moduleName,String beanId,String methodName){
  if (moduleName == null || beanId == null || methodName == null) {
    return;
  }
  Map<String,String> asyncBeanInfosInModule=asyncBeanInfos.get(moduleName);
  if (asyncBeanInfosInModule == null) {
    asyncBeanInfos.putIfAbsent(moduleName,new ConcurrentHashMap<String,String>());
    asyncBeanInfosInModule=asyncBeanInfos.get(moduleName);
  }
  asyncBeanInfosInModule.put(beanId,methodName);
}
