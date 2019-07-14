private static void initializeLanguages(){
  for (  Language language : LanguageRegistry.getLanguages()) {
    for (    LanguageVersion languageVersion : language.getVersions()) {
      LanguageVersionHandler languageVersionHandler=languageVersion.getLanguageVersionHandler();
      if (languageVersionHandler != null) {
        languageVersionHandler.getXPathHandler().initialize();
      }
    }
  }
}
