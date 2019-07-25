public boolean knows(@NotNull SLanguageId lang){
  return myLanguagesInUse.containsKey(lang);
}
