@NotNull @Override public List<? super PsiElement> process(@NotNull PsiClass psiClass){
  List<? super PsiElement> result=Collections.emptyList();
  PsiAnnotation psiAnnotation=PsiAnnotationSearchUtil.findAnnotation(psiClass,getSupportedAnnotationClasses());
  if (null != psiAnnotation) {
    if (supportAnnotationVariant(psiAnnotation) && validate(psiAnnotation,psiClass,ProblemEmptyBuilder.getInstance())) {
      result=new ArrayList<>();
      generatePsiElements(psiClass,psiAnnotation,result);
    }
  }
  return result;
}
