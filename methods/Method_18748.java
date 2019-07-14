/** 
 * Get the prop defaults from the given  {@link PsiClass}. 
 */
public static ImmutableList<PropDefaultModel> getPropDefaults(PsiClass psiClass){
  final List<PropDefaultModel> propDefaults=new ArrayList<>();
  for (  PsiField psiField : psiClass.getFields()) {
    propDefaults.addAll(extractFromField(psiField));
  }
  return ImmutableList.copyOf(propDefaults);
}
