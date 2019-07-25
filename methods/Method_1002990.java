@Override protected boolean validate(@NotNull PsiAnnotation psiAnnotation,@NotNull PsiClass psiClass,@NotNull ProblemBuilder builder){
  final boolean result=validateAnnotationOnRightType(psiClass,builder) && validateVisibility(psiAnnotation);
  if (PsiAnnotationUtil.getBooleanAnnotationValue(psiAnnotation,"lazy",false)) {
    builder.addWarning("'lazy' is not supported for @Getter on a type");
  }
  return result;
}
