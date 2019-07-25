@NotNull @Override public List<? super PsiElement> process(@NotNull PsiClass psiClass){
  List<? super PsiElement> result=Collections.emptyList();
  final PsiElement parentElement=psiClass.getParent();
  if (parentElement instanceof PsiClass && !(parentElement instanceof LombokLightClassBuilder)) {
    result=new ArrayList<>();
    final PsiClass psiParentClass=(PsiClass)parentElement;
    PsiAnnotation psiAnnotation=PsiAnnotationSearchUtil.findAnnotation(psiParentClass,getSupportedAnnotationClasses());
    if (null == psiAnnotation) {
      final Collection<PsiMethod> psiMethods=PsiClassUtil.collectClassMethodsIntern(psiParentClass);
      for (      PsiMethod psiMethod : psiMethods) {
        psiAnnotation=PsiAnnotationSearchUtil.findAnnotation(psiMethod,getSupportedAnnotationClasses());
        if (null != psiAnnotation) {
          processMethodAnnotation(result,psiMethod,psiAnnotation,psiClass,psiParentClass);
        }
      }
    }
 else {
      processMethodAnnotation(result,null,psiAnnotation,psiClass,psiParentClass);
    }
  }
  return result;
}
