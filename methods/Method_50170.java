private static String getArgName(Annotation[] annotations){
  for (  Annotation annotation : annotations) {
    if (annotation.annotationType().isAssignableFrom(Arg.class)) {
      return ((Arg)annotation).value();
    }
  }
  return "input";
}
