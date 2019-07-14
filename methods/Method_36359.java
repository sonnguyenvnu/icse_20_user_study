@SuppressWarnings("unchecked") private void addPostProcessors(DefaultListableBeanFactory beanFactory){
  Map<String,BeanDefinition> processors=(Map<String,BeanDefinition>)rootApplicationContext.getBean(SofaModuleFrameworkConstants.PROCESSORS_OF_ROOT_APPLICATION_CONTEXT);
  for (  Map.Entry<String,BeanDefinition> entry : processors.entrySet()) {
    if (!beanFactory.containsBeanDefinition(entry.getKey())) {
      beanFactory.registerBeanDefinition(entry.getKey(),entry.getValue());
    }
  }
}
