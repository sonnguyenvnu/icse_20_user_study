@Override public boolean hasSimpleProperty(final Object bean,final String property){
  return hasSimpleProperty(new BeanProperty(this,bean,property));
}
