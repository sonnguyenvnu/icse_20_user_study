/** 
 * {@link ScannedGenericBeanDefinition}{@link AnnotatedGenericBeanDefinition}{@link GenericBeanDefinition}{@link org.springframework.beans.factory.support.ChildBeanDefinition}{@link org.springframework.beans.factory.support.RootBeanDefinition}{@link org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.ConfigurationClassBeanDefinition}
 */
private void transformSofaBeanDefinition(String beanId,BeanDefinition beanDefinition,ConfigurableListableBeanFactory beanFactory){
  if (isFromConfigurationSource(beanDefinition)) {
    generateSofaServiceDefinitionOnMethod(beanId,(AnnotatedBeanDefinition)beanDefinition,beanFactory);
  }
 else {
    Class<?> beanClassType=resolveBeanClassType(beanDefinition);
    if (beanClassType == null) {
      SofaLogger.warn("Bean class type cant be resolved from bean of {0}",beanId);
      return;
    }
    generateSofaServiceDefinitionOnClass(beanId,beanClassType,beanDefinition,beanFactory);
  }
}
