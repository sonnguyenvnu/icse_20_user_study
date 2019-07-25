@Override protected boolean validate(@NotNull PsiAnnotation psiAnnotation,@NotNull PsiClass psiClass,@NotNull ProblemBuilder builder){
  final PsiAnnotation equalsAndHashCodeAnnotation=PsiAnnotationSearchUtil.findAnnotation(psiClass,EqualsAndHashCode.class);
  if (null == equalsAndHashCodeAnnotation) {
    equalsAndHashCodeProcessor.validateCallSuperParamExtern(psiAnnotation,psiClass,builder);
  }
  return validateAnnotationOnRightType(psiClass,builder);
}
