public static <T>T instantiate(Class<T> type){
  try {
    Constructor<T> constructor=type.getDeclaredConstructor();
    if (!constructor.isAccessible())     constructor.setAccessible(true);
    return constructor.newInstance();
  }
 catch (  Exception e) {
    return null;
  }
}
