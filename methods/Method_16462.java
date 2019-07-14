@Override public Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException {
  if (bean instanceof PersonnelAuthenticationSupplier) {
    PersonnelAuthenticationHolder.addSupplier(((PersonnelAuthenticationSupplier)bean));
  }
  return bean;
}
