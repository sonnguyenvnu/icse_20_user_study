/** 
 * register bean in spring ioc.
 * @param beanName bean name
 * @param obj      bean
 */
public void registerBean(final String beanName,final Object obj){
  AssertUtils.notNull(beanName);
  AssertUtils.notNull(obj);
  cfgContext.getBeanFactory().registerSingleton(beanName,obj);
}
