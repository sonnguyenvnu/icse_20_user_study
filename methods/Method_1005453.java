@Override public Configuration build(BeanContainer beanContainer){
  if (!StringUtils.isBlank(beanFactory)) {
    setBeanFactory(elEngine.resolve(beanFactory));
  }
  return super.build(beanContainer);
}
