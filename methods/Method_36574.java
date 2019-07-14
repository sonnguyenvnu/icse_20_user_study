@Override public Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException {
  processSofaReference(bean);
  return bean;
}
