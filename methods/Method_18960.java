private static boolean canCreateDiffModels(ExecutableElement method,List<Class<? extends Annotation>> delegateMethodAnnotationsThatSkipDiffModels){
  for (  Class<? extends Annotation> delegate : delegateMethodAnnotationsThatSkipDiffModels) {
    if (method.getAnnotation(delegate) != null) {
      return false;
    }
  }
  return true;
}
