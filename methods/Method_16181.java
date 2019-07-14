@Override public Object postProcessAfterInitialization(Object bean,String beanName){
  ScriptScope scope;
  if (bean instanceof Service) {
    addGlobalVariable(beanName,bean);
  }
 else   if ((scope=AnnotationUtils.findAnnotation(ClassUtils.getUserClass(bean),ScriptScope.class)) != null) {
    addGlobalVariable(!scope.value().isEmpty() ? scope.value() : beanName,bean);
  }
  return bean;
}
