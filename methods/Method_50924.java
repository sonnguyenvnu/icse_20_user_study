public static Language createLanguage(String language,Properties properties){
  Language implementation;
  if (BY_EXTENSION.equals(language)) {
    implementation=instance.getLanguageByExtension(properties.getProperty(EXTENSION));
  }
 else {
    implementation=instance.languages.get(instance.languageAliases(language).toLowerCase(Locale.ROOT));
  }
  if (implementation == null) {
    implementation=new AnyLanguage(language);
  }
  implementation.setProperties(properties);
  return implementation;
}
