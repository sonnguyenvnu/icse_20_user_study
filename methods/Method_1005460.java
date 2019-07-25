public DozerClass build(BeanContainer beanContainer){
  DozerClass dozerClass=new DozerClass(beanContainer);
  dozerClass.setName(clazz);
  dozerClass.setBeanFactory(beanFactory);
  dozerClass.setFactoryBeanId(factoryBeanId);
  dozerClass.setMapGetMethod(mapGetMethod);
  dozerClass.setMapSetMethod(mapSetMethod);
  dozerClass.setCreateMethod(createMethod);
  dozerClass.setMapNull(mapNull);
  dozerClass.setMapEmptyString(mapEmptyString);
  dozerClass.setAccessible(isAccessible);
  dozerClass.setSkipConstructor(skipConstructor);
  return dozerClass;
}
