private static Set<Language> getApplicableLanguages(PMDConfiguration configuration,RuleSets ruleSets){
  Set<Language> languages=new HashSet<>();
  LanguageVersionDiscoverer discoverer=configuration.getLanguageVersionDiscoverer();
  for (  Rule rule : ruleSets.getAllRules()) {
    Language language=rule.getLanguage();
    if (languages.contains(language)) {
      continue;
    }
    LanguageVersion version=discoverer.getDefaultLanguageVersion(language);
    if (RuleSet.applies(rule,version)) {
      languages.add(language);
      if (LOG.isLoggable(Level.FINE)) {
        LOG.fine("Using " + language.getShortName() + " version: " + version.getShortName());
      }
    }
  }
  return languages;
}
