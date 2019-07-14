private static ImmutableList<PropDefaultModel> extractFromField(PsiField psiField){
  final Annotation propDefaultAnnotation=PsiAnnotationProxyUtils.findAnnotationInHierarchy(psiField,PropDefault.class);
  if (propDefaultAnnotation == null) {
    return ImmutableList.of();
  }
  final ResType propDefaultResType=((PropDefault)propDefaultAnnotation).resType();
  final int propDefaultResId=((PropDefault)propDefaultAnnotation).resId();
  return ImmutableList.of(new PropDefaultModel(PsiTypeUtils.getTypeName(psiField.getType()),psiField.getName(),PsiProcessingUtils.extractModifiers(psiField.getModifierList()),psiField,propDefaultResType,propDefaultResId));
}
