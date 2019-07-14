private boolean checkMethodMetadataReadingVisitor(RootBeanDefinition beanDefinition){
  return beanDefinition.getSource() instanceof MethodMetadataReadingVisitor && ((MethodMetadataReadingVisitor)beanDefinition.getSource()).isAnnotated(SentinelRestTemplate.class.getName());
}
