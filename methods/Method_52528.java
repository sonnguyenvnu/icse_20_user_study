public static boolean isGeneric(Class<?> clazz){
  return clazz.getTypeParameters().length != 0;
}
