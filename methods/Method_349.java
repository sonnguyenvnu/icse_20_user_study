/** 
 * ???? ???????
 * @param bean
 * @param beanName
 * @return
 * @throws BeansException
 */
@Override public Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException {
  if ("annotationBean".equals(beanName)) {
    LOGGER.info("SpringLifeCycleProcessor start beanName={}",beanName);
  }
  return bean;
}
