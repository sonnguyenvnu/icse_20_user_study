@Override public Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException {
  if (bean instanceof DefaultPropertyCopier) {
    mapperEntityFactory.setDefaultPropertyCopier(((DefaultPropertyCopier)bean));
  }
 else   if (bean instanceof PropertyCopier) {
    mapperEntityFactory.addCopier(((PropertyCopier)bean));
  }
  if (bean instanceof EntityMappingCustomizer) {
    ((EntityMappingCustomizer)bean).customize(mapperEntityFactory);
  }
  return bean;
}
