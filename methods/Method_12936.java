/** 
 * Extract the filename from the given Java resource path, e.g.  {@code "mypath/myfile.txt" -> "myfile.txt"}.
 * @param path the file path (may be {@code null})
 * @return the extracted filename, or {@code null} if none
 */
public static String getFilename(String path){
  if (path == null) {
    return null;
  }
  int separatorIndex=path.lastIndexOf(FOLDER_SEPARATOR);
  return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
}
