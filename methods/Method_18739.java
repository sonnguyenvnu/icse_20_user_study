/** 
 * @param psiClass to extract fields from. It will not be modified.
 * @return the list of {@link FieldModel}s of the given  {@link PsiClass} or empty list if thereare no fields.
 */
static ImmutableList<FieldModel> extractFields(PsiClass psiClass){
  return Optional.of(psiClass).map(PsiClass::getFields).map(Arrays::stream).map(fields -> fields.filter(Objects::nonNull).map(PsiFieldsExtractor::createFieldModel).collect(Collectors.toCollection(ImmutableList::of))).orElse(ImmutableList.of());
}
