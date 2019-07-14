/** 
 * Starts with defining injection points (i.e. wiring) for existing bean.
 */
public BeanWire wire(final String beanName){
  petiteContainer.lookupExistingBeanDefinition(beanName);
  return new BeanWire(beanName);
}
