private void scan(final Collection<Class<?>> managedBeans){
  for (  Class<?> beanClazz : managedBeans) {
    log.debug("Scanning class " + beanClazz.getName());
    scanInjectableConstructors(beanClazz);
    scanInjectableFields(beanClazz);
  }
}
