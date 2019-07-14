public static boolean isGeneric(Method method){
  return method.getTypeParameters().length != 0;
}
