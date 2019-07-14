/** 
 * Set the given LanguageVersion as the current default for it's Language.
 * @param languageVersion The new default for the Language.
 * @return The previous default version for the language.
 */
public LanguageVersion setDefaultLanguageVersion(LanguageVersion languageVersion){
  LanguageVersion currentLanguageVersion=languageToLanguageVersion.put(languageVersion.getLanguage(),languageVersion);
  if (currentLanguageVersion == null) {
    currentLanguageVersion=languageVersion.getLanguage().getDefaultVersion();
  }
  return currentLanguageVersion;
}
