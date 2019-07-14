private void determineLanguage(RuleContext ctx){
  if (ctx.getLanguageVersion() == null) {
    LanguageVersion languageVersion=configuration.getLanguageVersionOfFile(ctx.getSourceCodeFilename());
    ctx.setLanguageVersion(languageVersion);
  }
}
