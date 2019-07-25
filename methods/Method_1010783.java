@NotNull @Override public Collection<TransformationMenu> lookup(@NotNull Collection<SLanguage> usedLanguages,@NotNull String menuLocation){
  return Collections.singleton(new ImplicitTransformationMenu(myConcept));
}
