private static String getKey(Class<?> clazz,String name){
  return String.format("%s:%s",clazz.getCanonicalName(),name);
}
