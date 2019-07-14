/** 
 * Gets the annotation attributes  {@link RestTemplate} bean being annotated{@link DubboTransported @DubboTransported}
 * @param beanName           the bean name of {@link LoadBalanced @LoadBalanced} {@link RestTemplate}
 * @param attributesResolver {@link DubboTransportedAttributesResolver}
 * @return non-null {@link Map}
 */
private Map<String,Object> getDubboTranslatedAttributes(String beanName,DubboTransportedAttributesResolver attributesResolver){
  Map<String,Object> attributes=Collections.emptyMap();
  BeanDefinition beanDefinition=beanFactory.getBeanDefinition(beanName);
  if (beanDefinition instanceof AnnotatedBeanDefinition) {
    AnnotatedBeanDefinition annotatedBeanDefinition=(AnnotatedBeanDefinition)beanDefinition;
    MethodMetadata factoryMethodMetadata=annotatedBeanDefinition.getFactoryMethodMetadata();
    attributes=factoryMethodMetadata != null ? factoryMethodMetadata.getAnnotationAttributes(DUBBO_TRANSPORTED_CLASS_NAME) : Collections.emptyMap();
  }
  return attributesResolver.resolve(attributes);
}
