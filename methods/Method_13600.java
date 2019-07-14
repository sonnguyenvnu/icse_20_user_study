private boolean checkSentinelProtect(RootBeanDefinition beanDefinition,Class<?> beanType){
  return beanType == RestTemplate.class && (checkStandardMethodMetadata(beanDefinition) || checkMethodMetadataReadingVisitor(beanDefinition));
}
