public static boolean isAnnotatedWith(MethodParamModel methodParamModel,Class<? extends Annotation> annotationClass){
  for (  Annotation annotation : methodParamModel.getAnnotations()) {
    if (annotation.annotationType().equals(annotationClass)) {
      return true;
    }
  }
  return false;
}
