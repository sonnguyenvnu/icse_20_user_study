/** 
 * Normalizes the filename by taking the casing into account, e.g. on Windows, the filename is changed to lowercase only.
 * @param fileName the file name
 * @return the normalized file name
 */
public static String normalizeFilename(String fileName){
  if (fileName != null && File.separatorChar == '\\') {
    return fileName.toLowerCase(Locale.ROOT);
  }
  return fileName;
}
