/** 
 * Set the given LanguageVersions as the current default for their Languages.
 * @param languageVersions The LanguageVersions.
 */
public void setDefaultLanguageVersions(List<LanguageVersion> languageVersions){
  for (  LanguageVersion languageVersion : languageVersions) {
    languageVersionDiscoverer.setDefaultLanguageVersion(languageVersion);
  }
}
