@NotNull @Override public List<? super PsiElement> process(@NotNull PsiClass psiClass){
  List<? super PsiElement> result=new ArrayList<>();
  for (  PsiMethod psiMethod : PsiClassUtil.collectClassMethodsIntern(psiClass)) {
    PsiAnnotation psiAnnotation=PsiAnnotationSearchUtil.findAnnotation(psiMethod,getSupportedAnnotationClasses());
    if (null != psiAnnotation) {
      if (validate(psiAnnotation,psiMethod,ProblemEmptyBuilder.getInstance())) {
        processIntern(psiMethod,psiAnnotation,result);
      }
    }
  }
  return result;
}
