public static <T>T invokeTarget(final String name,final Object value,final Class<T> clazz,final Class<?> argClass){
  try {
    Method method=Moco.class.getMethod(name,argClass);
    Object result=method.invoke(null,value);
    return clazz.cast(result);
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
