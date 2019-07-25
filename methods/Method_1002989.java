@Override protected boolean validate(@NotNull PsiAnnotation psiAnnotation,@NotNull PsiClass psiClass,@NotNull ProblemBuilder builder){
  final String typeName=FieldNameConstantsHandler.getTypeName(psiClass,psiAnnotation);
  Optional<PsiClass> innerClass=PsiClassUtil.getInnerClassInternByName(psiClass,typeName);
  if (innerClass.isPresent()) {
    final boolean asEnum=PsiAnnotationUtil.getBooleanAnnotationValue(psiAnnotation,"asEnum",false);
    if (innerClass.get().isEnum() != asEnum) {
      builder.addError("@FieldNameConstants inner type already exists, but asEnum=" + asEnum + " does not match existing type");
      return false;
    }
  }
  return true;
}
