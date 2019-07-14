/** 
 * Get the Languages of a given source file.
 * @param fileName The file name.
 * @return The Languages for the source file, may be empty.
 */
public List<Language> getLanguagesForFile(String fileName){
  String extension=getExtension(fileName);
  return LanguageRegistry.findByExtension(extension);
}
