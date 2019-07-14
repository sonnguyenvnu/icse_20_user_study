public String getLanguage(){
  return language != null ? language : LanguageRegistry.getDefaultLanguage().getTerseName();
}
