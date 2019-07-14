/** 
 * Get the LanguageVersion for the first Language of a source file with the given name.
 * @param fileName The file name.
 * @return The currently configured LanguageVersion for the source file or<code>null</code> if there are no supported Languages for the file.
 */
public LanguageVersion getDefaultLanguageVersionForFile(String fileName){
  List<Language> languages=getLanguagesForFile(fileName);
  LanguageVersion languageVersion=null;
  if (!languages.isEmpty()) {
    languageVersion=getDefaultLanguageVersion(languages.get(0));
  }
  return languageVersion;
}
