/** 
 * Starts registration of init method.
 */
public BeanInit init(final String beanName){
  petiteContainer.lookupExistingBeanDefinition(beanName);
  return new BeanInit(beanName);
}
