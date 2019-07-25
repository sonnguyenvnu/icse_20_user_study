@NotNull public static AccessorsInfo build(@Nullable PsiClass psiClass){
  PsiClass containingClass=psiClass;
  while (null != containingClass) {
    final PsiAnnotation accessorsClassAnnotation=PsiAnnotationSearchUtil.findAnnotation(containingClass,Accessors.class);
    if (null != accessorsClassAnnotation) {
      return buildFromAnnotation(accessorsClassAnnotation,containingClass);
    }
    containingClass=containingClass.getContainingClass();
  }
  return buildAccessorsInfo(psiClass,null,null,Collections.emptySet());
}
