@NotNull @Override public Collection<TransformationMenu> lookup(@NotNull Collection<SLanguage> usedLanguages,@NotNull String menuLocation){
  EditorAspectDescriptor aspectDescriptor=LanguageRegistryHelper.getEditorAspectDescriptor(myLanguageRegistry,myId.getConcept().getLanguage());
  if (aspectDescriptor == null) {
    return Collections.emptyList();
  }
  return aspectDescriptor.getNamedTransformationMenus(myId,usedLanguages);
}
