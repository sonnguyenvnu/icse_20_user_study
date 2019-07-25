@NotNull public static AccessorsInfo build(@NotNull PsiField psiField,@NotNull AccessorsInfo classAccessorsInfo){
  final PsiAnnotation accessorsFieldAnnotation=PsiAnnotationSearchUtil.findAnnotation(psiField,Accessors.class);
  if (null != accessorsFieldAnnotation) {
    return buildFromAnnotation(accessorsFieldAnnotation,psiField.getContainingClass());
  }
 else {
    return classAccessorsInfo;
  }
}
