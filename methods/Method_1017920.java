public static ServiceLevelAgreementAction instantiate(Class<? extends ServiceLevelAgreementAction> clazz){
  ServiceLevelAgreementAction action=null;
  try {
    action=SpringApplicationContext.getBean(clazz);
  }
 catch (  NoSuchBeanDefinitionException e) {
  }
  if (action == null) {
    try {
      action=ConstructorUtils.invokeConstructor(clazz,null);
    }
 catch (    NoSuchMethodException|IllegalAccessException|InvocationTargetException|InstantiationException e) {
      log.error("Error invoking constructor",e);
    }
  }
  return action;
}
