private boolean checkStandardMethodMetadata(RootBeanDefinition beanDefinition){
  return beanDefinition.getSource() instanceof StandardMethodMetadata && ((StandardMethodMetadata)beanDefinition.getSource()).isAnnotated(SentinelRestTemplate.class.getName());
}
