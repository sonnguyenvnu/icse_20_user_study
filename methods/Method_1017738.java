public static <T>T instantiate(Constructor<T> ctor,Object... args){
  try {
    return ctor.newInstance(args);
  }
 catch (  Exception ex) {
    throw reflectionException(ex);
  }
}
