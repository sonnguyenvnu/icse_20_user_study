void initialize(LanguageRegistry registry){
  Queue<SLanguage> extendedLanguageIDs=new ArrayDeque<>();
  fillExtendedLanguages(extendedLanguageIDs);
  Set<SLanguageId> visitedLanguages=new HashSet<>();
  visitedLanguages.add(getId());
  while (!extendedLanguageIDs.isEmpty()) {
    SLanguage nextLanguageID=extendedLanguageIDs.remove();
    LanguageRuntime extendedLanguage=registry.getLanguage(nextLanguageID);
    if (extendedLanguage != null && visitedLanguages.add(extendedLanguage.getId())) {
      extendedLanguage.registerExtendingLanguage(this);
      extendedLanguage.fillExtendedLanguages(extendedLanguageIDs);
    }
  }
  myExtendingLanguages.remove(this);
  myExtendedLanguages.remove(this);
  LanguageRuntime langCore=registry.getLanguage(BootstrapLanguages.getLangCore());
  assert langCore != null;
  if (this != langCore && !visitedLanguages.contains(langCore.getId())) {
    langCore.registerExtendingLanguage(this);
  }
}
