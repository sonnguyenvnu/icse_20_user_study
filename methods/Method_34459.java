private Object getPropertyValue(String name,Object obj) throws HystrixCacheKeyGenerationException {
  try {
    return new PropertyDescriptor(name,obj.getClass()).getReadMethod().invoke(obj);
  }
 catch (  IllegalAccessException e) {
    throw new HystrixCacheKeyGenerationException(e);
  }
catch (  IntrospectionException e) {
    throw new HystrixCacheKeyGenerationException(e);
  }
catch (  InvocationTargetException e) {
    throw new HystrixCacheKeyGenerationException(e);
  }
}
