public static boolean isDecimal(Class<?> clazz){
  return clazz.equals(Double.class) || clazz.equals(Float.class);
}
