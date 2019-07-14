/** 
 * {@link org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.ConfigurationClassBeanDefinition}
 * @param beanDefinition Check whether it is a bean definition created from a configuration classas opposed to any other configuration source.
 * @return
 */
private boolean isFromConfigurationSource(BeanDefinition beanDefinition){
  return beanDefinition.getClass().getCanonicalName().startsWith("org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader");
}
