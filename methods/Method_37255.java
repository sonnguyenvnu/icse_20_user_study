@Override public Class<?> getPropertyType(final Object bean,final String name){
  BeanProperty beanProperty=new BeanProperty(this,bean,name);
  if (!resolveExistingNestedProperties(beanProperty)) {
    return null;
  }
  hasIndexProperty(beanProperty);
  return extractType(beanProperty);
}
