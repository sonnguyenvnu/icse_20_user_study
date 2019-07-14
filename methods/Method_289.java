private static boolean isRequired(Method method){
  return method.getAnnotation(Optional.class) == null;
}
