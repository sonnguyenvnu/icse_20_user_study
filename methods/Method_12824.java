/** 
 * Checks a file to make sure it should be packaged as standard resources.
 * @param fileName the name of the file (including extension)
 * @param extension the extension of the file (excluding '.')
 * @param allowClassFiles whether to allow java class files
 * @return true if the file should be packaged as standard java resources.
 */
public static boolean checkFileForPackaging(@NonNull String fileName,@NonNull String extension,boolean allowClassFiles){
  return !(fileName.charAt(0) == '.' || fileName.charAt(fileName.length() - 1) == '~') && !isOfNonResourcesExtensions(extension,allowClassFiles) && !isNotAResourceFile(fileName);
}
