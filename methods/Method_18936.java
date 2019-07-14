public static boolean hasAnnotation(MethodParamModel methodParam,Class<?> annotationClass){
  for (  Annotation annotation : methodParam.getAnnotations()) {
    if (annotation.annotationType().equals(annotationClass)) {
      return true;
    }
  }
  return false;
}
