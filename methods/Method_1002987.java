@Override protected boolean validate(@NotNull PsiAnnotation psiAnnotation,@NotNull PsiClass psiClass,@NotNull ProblemBuilder builder){
  return validateAnnotationOnRightType(psiClass,builder) && LombokProcessorUtil.isLevelVisible(psiAnnotation);
}
