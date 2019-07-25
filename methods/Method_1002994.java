@NotNull @Override public List<? super PsiElement> process(@NotNull PsiClass psiClass){
  List<? super PsiElement> result=new ArrayList<>();
  for (  PsiField psiField : PsiClassUtil.collectClassFieldsIntern(psiClass)) {
    PsiAnnotation psiAnnotation=PsiAnnotationSearchUtil.findAnnotation(psiField,getSupportedAnnotationClasses());
    if (null != psiAnnotation) {
      if (validate(psiAnnotation,psiField,ProblemEmptyBuilder.getInstance())) {
        generatePsiElements(psiField,psiAnnotation,result);
      }
    }
  }
  return result;
}
