public static <T>T instantiate(Class<T> clazz){
  try {
    return clazz.newInstance();
  }
 catch (  Exception ex) {
    throw reflectionException(ex);
  }
}
