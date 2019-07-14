@Nullable private static Annotation getInterStageInputAnnotation(MethodParamModel methodParamModel,ImmutableList<Class<? extends Annotation>> validAnnotations){
  for (  Annotation annotation : methodParamModel.getAnnotations()) {
    if (validAnnotations.contains(annotation.annotationType())) {
      return annotation;
    }
  }
  return null;
}
