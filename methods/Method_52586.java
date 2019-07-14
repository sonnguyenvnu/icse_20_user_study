public static boolean isA(TypedNameDeclaration vnd,String className){
  Class<?> type=vnd.getType();
  if (type != null) {
    Class<?> clazz=loadClass(type.getClassLoader(),className);
    if (clazz != null) {
      return clazz.isAssignableFrom(type);
    }
  }
  return false;
}
