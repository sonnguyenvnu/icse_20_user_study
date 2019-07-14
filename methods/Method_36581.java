@SuppressWarnings("unchecked") private void doGenerateSofaReferenceDefinition(BeanDefinition beanDefinition,SofaReference sofaReference,Class<?> parameterType,ConfigurableListableBeanFactory beanFactory){
  Assert.isTrue(JvmBinding.JVM_BINDING_TYPE.getType().equals(sofaReference.binding().bindingType()),"Only jvm type of @SofaReference on parameter is supported.");
  AnnotationWrapperBuilder<SofaReference> wrapperBuilder=AnnotationWrapperBuilder.wrap(sofaReference).withBinder(binder);
  sofaReference=wrapperBuilder.build();
  Class<?> interfaceType=sofaReference.interfaceType();
  if (interfaceType.equals(void.class)) {
    interfaceType=parameterType;
  }
  String uniqueId=sofaReference.uniqueId();
  String referenceId=SofaBeanNameGenerator.generateSofaReferenceBeanName(interfaceType,uniqueId);
  if (!beanFactory.containsBeanDefinition(referenceId)) {
    BeanDefinitionBuilder builder=BeanDefinitionBuilder.genericBeanDefinition();
    builder.getRawBeanDefinition().setScope(beanDefinition.getScope());
    builder.getRawBeanDefinition().setLazyInit(beanDefinition.isLazyInit());
    builder.getRawBeanDefinition().setBeanClass(ReferenceFactoryBean.class);
    builder.addPropertyValue(AbstractContractDefinitionParser.UNIQUE_ID_PROPERTY,uniqueId);
    builder.addPropertyValue(AbstractContractDefinitionParser.INTERFACE_CLASS_PROPERTY,interfaceType);
    builder.addPropertyValue(AbstractContractDefinitionParser.BINDINGS,getSofaReferenceBinding(sofaReference,sofaReference.binding()));
    builder.addPropertyValue(AbstractContractDefinitionParser.DEFINITION_BUILDING_API_TYPE,true);
    ((BeanDefinitionRegistry)beanFactory).registerBeanDefinition(referenceId,builder.getBeanDefinition());
  }
  if (beanDefinition.getDependsOn() == null) {
    beanDefinition.setDependsOn(referenceId);
  }
 else {
    String[] added=ObjectUtils.addObjectToArray(beanDefinition.getDependsOn(),referenceId);
    beanDefinition.setDependsOn(added);
  }
}
