static FieldModel createFieldModel(PsiField psiField){
  return new FieldModel(FieldSpec.builder(PsiTypeUtils.getTypeName(psiField.getType()),psiField.getName(),PsiProcessingUtils.extractModifiers(psiField)).build(),psiField);
}
