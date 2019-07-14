/** 
 * Checks a file to make sure it should be packaged as standard resources.
 * @param fileName the name of the file (including extension)
 * @param extension the extension of the file (excluding '.')
 * @return true if the file should be packaged as standard java resources.
 */
public static boolean checkFileForPackaging(@NonNull String fileName,@NonNull String extension){
  return !(fileName.charAt(0) == '.' || fileName.charAt(fileName.length() - 1) == '~') && !isOfNonResourcesExtensions(extension,false) && !isNotAResourceFile(fileName);
}
