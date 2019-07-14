@Override public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,BeanDefinitionRegistry registry){
  final String beanName="strawberry";
  boolean contain=registry.containsBeanDefinition(beanName);
  if (!contain) {
    RootBeanDefinition rootBeanDefinition=new RootBeanDefinition(Strawberry.class);
    registry.registerBeanDefinition(beanName,rootBeanDefinition);
  }
}
