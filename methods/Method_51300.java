/** 
 * Get the LanguageVersion of the source file with given name. This depends on the fileName extension, and the java version. <p> For compatibility with older code that does not always pass in a correct filename, unrecognized files are assumed to be java files. </p>
 * @param fileName Name of the file, can be absolute, or simple.
 * @return the LanguageVersion
 */
public LanguageVersion getLanguageVersionOfFile(String fileName){
  LanguageVersion languageVersion=languageVersionDiscoverer.getDefaultLanguageVersionForFile(fileName);
  if (languageVersion == null) {
    languageVersion=languageVersionDiscoverer.getDefaultLanguageVersion(LanguageRegistry.getLanguage("Java"));
  }
  return languageVersion;
}
