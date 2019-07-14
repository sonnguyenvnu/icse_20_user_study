@Override public Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException {
  if (bean instanceof SofaRuntimeContextAware) {
    ((SofaRuntimeContextAware)bean).setSofaRuntimeContext(sofaRuntimeContext);
  }
  return bean;
}
