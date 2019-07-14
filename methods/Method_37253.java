@Override public boolean hasProperty(final Object bean,final String name){
  BeanProperty beanProperty=new BeanProperty(this,bean,name);
  if (!resolveExistingNestedProperties(beanProperty)) {
    return false;
  }
  return hasIndexProperty(beanProperty);
}
