@Override public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition,Class<?> beanType,String beanName){
  if (checkSentinelProtect(beanDefinition,beanType)) {
    SentinelRestTemplate sentinelRestTemplate;
    if (beanDefinition.getSource() instanceof StandardMethodMetadata) {
      sentinelRestTemplate=((StandardMethodMetadata)beanDefinition.getSource()).getIntrospectedMethod().getAnnotation(SentinelRestTemplate.class);
    }
 else {
      sentinelRestTemplate=beanDefinition.getResolvedFactoryMethod().getAnnotation(SentinelRestTemplate.class);
    }
    checkSentinelRestTemplate(sentinelRestTemplate,beanName);
    cache.put(beanName,sentinelRestTemplate);
  }
}
