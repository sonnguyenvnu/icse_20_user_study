public static String getAsyncInitMethodName(String moduleName,String beanId){
  Map<String,String> asyncBeanInfosInModule;
  asyncBeanInfosInModule=(moduleName == null) ? null : asyncBeanInfos.get(moduleName);
  return (beanId == null || asyncBeanInfosInModule == null) ? null : asyncBeanInfosInModule.get(beanId);
}
