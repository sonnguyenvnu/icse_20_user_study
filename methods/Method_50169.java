private static boolean isArg(Annotation[] annotations){
  for (  Annotation annotation : annotations) {
    if (annotation.annotationType().isAssignableFrom(Arg.class)) {
      return true;
    }
  }
  return false;
}
