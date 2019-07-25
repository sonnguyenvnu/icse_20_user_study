@NotNull @Override public List<? super PsiElement> process(@NotNull PsiClass psiClass){
  if (psiClass.getParent() instanceof PsiClass) {
    PsiClass parentClass=(PsiClass)psiClass.getParent();
    PsiAnnotation psiAnnotation=PsiAnnotationSearchUtil.findAnnotation(parentClass,getSupportedAnnotationClasses());
    if (null != psiAnnotation && supportAnnotationVariant(psiAnnotation)) {
      ProblemEmptyBuilder problemBuilder=ProblemEmptyBuilder.getInstance();
      if (super.validate(psiAnnotation,parentClass,problemBuilder)) {
        final String typeName=FieldNameConstantsHandler.getTypeName(parentClass,psiAnnotation);
        if (typeName.equals(psiClass.getName())) {
          if (validate(psiAnnotation,parentClass,problemBuilder)) {
            List<? super PsiElement> result=new ArrayList<>();
            generatePsiElements(parentClass,psiClass,psiAnnotation,result);
            return result;
          }
        }
      }
    }
  }
  return Collections.emptyList();
}
