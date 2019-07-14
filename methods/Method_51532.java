private void loadLanguageMinMaxVersions(Rule rule){
  if (minimumVersion != null) {
    LanguageVersion minimumLanguageVersion=rule.getLanguage().getVersion(minimumVersion);
    if (minimumLanguageVersion == null) {
      throwUnknownLanguageVersionException("minimum",minimumVersion);
    }
 else {
      rule.setMinimumLanguageVersion(minimumLanguageVersion);
    }
  }
  if (maximumVersion != null) {
    LanguageVersion maximumLanguageVersion=rule.getLanguage().getVersion(maximumVersion);
    if (maximumLanguageVersion == null) {
      throwUnknownLanguageVersionException("maximum",maximumVersion);
    }
 else {
      rule.setMaximumLanguageVersion(maximumLanguageVersion);
    }
  }
  checkLanguageVersionsAreOrdered(rule);
}
