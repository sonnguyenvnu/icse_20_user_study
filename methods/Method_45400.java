public static <T>T invokeTarget(final String name,final Object arg1,final Object arg2,final Class<T> clazz,final Class<?> arg1Class,final Class<?> arg2Class){
  try {
    Method method=Moco.class.getMethod(name,arg1Class,arg2Class);
    Object result=method.invoke(null,arg1,arg2);
    return clazz.cast(result);
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
