public static boolean isWholeNumber(Class<?> clazz){
  return clazz.equals(Long.class) || clazz.equals(Integer.class) || clazz.equals(Short.class) || clazz.equals(Byte.class);
}
