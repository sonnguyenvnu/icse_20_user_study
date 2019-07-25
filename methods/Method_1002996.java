@NotNull public static AccessorsInfo build(@NotNull PsiVariable psiVariable,@Nullable PsiClass containingClass){
  final PsiAnnotation accessorsFieldAnnotation=PsiAnnotationSearchUtil.findAnnotation(psiVariable,Accessors.class);
  if (null != accessorsFieldAnnotation) {
    return buildFromAnnotation(accessorsFieldAnnotation,containingClass);
  }
 else {
    return build(containingClass);
  }
}
