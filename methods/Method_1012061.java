public void init(Collection<SLanguage> usedLanguages,Collection<SModuleReference> usedDevKits){
  myInitialLanguages=usedLanguages;
  myInitialDevKits=usedDevKits;
  myLanguageItems.addAll(usedLanguages);
  myDevKitItems.addAll(usedDevKits);
}
