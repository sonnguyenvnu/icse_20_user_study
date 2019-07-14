@Override public boolean hasRootProperty(final Object bean,String name){
  int dotNdx=indexOfDot(name);
  if (dotNdx != -1) {
    name=name.substring(0,dotNdx);
  }
  BeanProperty beanProperty=new BeanProperty(this,bean,name);
  extractIndex(beanProperty);
  return hasSimpleProperty(beanProperty);
}
