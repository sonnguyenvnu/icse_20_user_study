private static LanguageVersion[] getSupportedLanguageVersions(){
  List<LanguageVersion> languageVersions=new ArrayList<>();
  for (  LanguageVersion languageVersion : LanguageRegistry.findAllVersions()) {
    LanguageVersionHandler languageVersionHandler=languageVersion.getLanguageVersionHandler();
    if (languageVersionHandler != null) {
      Parser parser=languageVersionHandler.getParser(languageVersionHandler.getDefaultParserOptions());
      if (parser != null && parser.canParse()) {
        languageVersions.add(languageVersion);
      }
    }
  }
  return languageVersions.toArray(new LanguageVersion[0]);
}
