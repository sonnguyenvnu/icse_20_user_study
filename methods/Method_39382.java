/** 
 * Starts registration of destroy method.
 */
public BeanDestroy destroy(final String beanName){
  petiteContainer.lookupExistingBeanDefinition(beanName);
  return new BeanDestroy(beanName);
}
