private void generateSofaServiceDefinitionOnClass(String beanId,Class<?> beanClass,BeanDefinition beanDefinition,ConfigurableListableBeanFactory beanFactory){
  SofaService sofaServiceAnnotation=beanClass.getAnnotation(SofaService.class);
  generateSofaServiceDefinition(beanId,sofaServiceAnnotation,beanClass,beanDefinition,beanFactory);
}
