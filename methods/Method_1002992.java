@Override protected boolean validate(@NotNull PsiAnnotation psiAnnotation,@NotNull PsiClass psiClass,@NotNull ProblemBuilder builder){
  return validateAnnotationOnRightType(psiAnnotation,psiClass,builder) && validateVisibility(psiAnnotation);
}
