/** 
 * Lookups for  {@link BeanDefinition bean definition}. Returns <code>null</code> if bean name doesn't exist.
 */
public BeanDefinition lookupBeanDefinition(final String name){
  BeanDefinition beanDefinition=beans.get(name);
  if (beanDefinition == null) {
    if (petiteConfig.isUseAltBeanNames()) {
      beanDefinition=beansAlt.get(name);
    }
  }
  return beanDefinition;
}
