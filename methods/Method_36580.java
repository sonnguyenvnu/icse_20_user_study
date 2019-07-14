private void generateSofaReferenceDefinition(String beanId,Method method,ConfigurableListableBeanFactory beanFactory){
  Class<?>[] parameterTypes=method.getParameterTypes();
  Annotation[][] parameterAnnotations=method.getParameterAnnotations();
  for (int i=0; i < parameterAnnotations.length; ++i) {
    for (    Annotation annotation : parameterAnnotations[i]) {
      if (annotation instanceof SofaReference) {
        doGenerateSofaReferenceDefinition(beanFactory.getBeanDefinition(beanId),(SofaReference)annotation,parameterTypes[i],beanFactory);
      }
    }
  }
}
