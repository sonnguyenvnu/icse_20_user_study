@NotNull @Override public Collection<SubstituteMenu> lookup(@NotNull Collection<SLanguage> usedLanguages){
  EditorAspectDescriptor aspectDescriptor=LanguageRegistryHelper.getEditorAspectDescriptor(myLanguageRegistry,myId.getConcept().getLanguage());
  if (aspectDescriptor == null) {
    return Collections.emptyList();
  }
  return aspectDescriptor.getNamedSubstituteMenus(getId(),usedLanguages);
}
