/** 
 * Returns value of bean's property.
 */
@Override public <T>T getProperty(final Object bean,final String name){
  BeanProperty beanProperty=new BeanProperty(this,bean,name);
  if (!isSilent) {
    resolveNestedProperties(beanProperty);
    return (T)getIndexProperty(beanProperty);
  }
 else {
    try {
      resolveNestedProperties(beanProperty);
      return (T)getIndexProperty(beanProperty);
    }
 catch (    Exception ignore) {
      return null;
    }
  }
}
