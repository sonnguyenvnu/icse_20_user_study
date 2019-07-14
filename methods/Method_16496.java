@Override public Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException {
  if (bean instanceof RelationTargetSupplier) {
    RelationTargetHolder.addSupplier(((RelationTargetSupplier)bean));
  }
  return bean;
}
