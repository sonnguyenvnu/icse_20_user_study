@SuppressWarnings("unchecked") private void generateSofaServiceDefinition(String beanId,SofaService sofaServiceAnnotation,Class<?> beanClass,BeanDefinition beanDefinition,ConfigurableListableBeanFactory beanFactory){
  if (sofaServiceAnnotation == null) {
    return;
  }
  AnnotationWrapperBuilder<SofaService> wrapperBuilder=AnnotationWrapperBuilder.wrap(sofaServiceAnnotation).withBinder(binder);
  sofaServiceAnnotation=wrapperBuilder.build();
  Class<?> interfaceType=sofaServiceAnnotation.interfaceType();
  if (interfaceType.equals(void.class)) {
    Class<?> interfaces[]=beanClass.getInterfaces();
    if (beanClass.isInterface() || interfaces == null || interfaces.length == 0) {
      interfaceType=beanClass;
    }
 else     if (interfaces.length == 1) {
      interfaceType=interfaces[0];
    }
 else {
      throw new FatalBeanException("Bean " + beanId + " has more than one interface.");
    }
  }
  BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition();
  String serviceId=SofaBeanNameGenerator.generateSofaServiceBeanName(interfaceType,sofaServiceAnnotation.uniqueId());
  if (!beanFactory.containsBeanDefinition(serviceId)) {
    builder.getRawBeanDefinition().setScope(beanDefinition.getScope());
    builder.setLazyInit(beanDefinition.isLazyInit());
    builder.getRawBeanDefinition().setBeanClass(ServiceFactoryBean.class);
    builder.addPropertyValue(AbstractContractDefinitionParser.INTERFACE_CLASS_PROPERTY,interfaceType);
    builder.addPropertyValue(AbstractContractDefinitionParser.UNIQUE_ID_PROPERTY,sofaServiceAnnotation.uniqueId());
    builder.addPropertyValue(AbstractContractDefinitionParser.BINDINGS,getSofaServiceBinding(sofaServiceAnnotation,sofaServiceAnnotation.bindings()));
    builder.addPropertyReference(ServiceDefinitionParser.REF,beanId);
    builder.addPropertyValue(ServiceDefinitionParser.BEAN_ID,beanId);
    builder.addPropertyValue(AbstractContractDefinitionParser.DEFINITION_BUILDING_API_TYPE,true);
    builder.addDependsOn(beanId);
    ((BeanDefinitionRegistry)beanFactory).registerBeanDefinition(serviceId,builder.getBeanDefinition());
  }
 else {
    SofaLogger.error("SofaService was already registered: {0}",serviceId);
  }
}
