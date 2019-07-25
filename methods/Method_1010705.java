@NotNull @Override public Collection<SubstituteMenu> lookup(@NotNull Collection<SLanguage> usedLanguages){
  List<SubstituteMenu> conceptMenu=new ArrayList<>();
  conceptMenu.addAll(getForConcept(usedLanguages));
  if (usedLanguages.contains(myConcept.getLanguage()) && conceptMenu.stream().allMatch(Menu::isContribution)) {
    conceptMenu.add(0,createImplicitMenu());
  }
  return conceptMenu;
}
