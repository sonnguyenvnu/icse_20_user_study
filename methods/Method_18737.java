static ImmutableList<FieldModel> getFields(PsiClass psiClass){
  final List<FieldModel> fieldModels=new ArrayList<>();
  for (  PsiField psiField : psiClass.getFields()) {
    fieldModels.add(new FieldModel(FieldSpec.builder(PsiTypeUtils.getTypeName(psiField.getType()),psiField.getName(),PsiProcessingUtils.extractModifiers(psiField)).build(),psiField));
  }
  return ImmutableList.copyOf(fieldModels);
}
