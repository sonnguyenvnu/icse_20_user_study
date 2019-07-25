@Override protected boolean validate(@NotNull PsiAnnotation psiAnnotation,@NotNull PsiField psiField,@NotNull ProblemBuilder builder){
  return LombokProcessorUtil.isLevelVisible(psiAnnotation) && checkIfFieldNameIsValidAndWarn(psiAnnotation,psiField,builder);
}
