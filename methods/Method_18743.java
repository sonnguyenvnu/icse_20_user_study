private static boolean canCreateDiffModels(PsiMethod method,List<Class<? extends Annotation>> delegateMethodAnnotationsThatSkipDiffModels){
  for (  Class<? extends Annotation> delegate : delegateMethodAnnotationsThatSkipDiffModels) {
    if (AnnotationUtil.findAnnotationInHierarchy(method,delegate) != null) {
      return false;
    }
  }
  return true;
}
