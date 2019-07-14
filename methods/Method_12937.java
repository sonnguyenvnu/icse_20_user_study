/** 
 * Strip the filename extension from the given Java resource path,
 * @param path the file path
 * @return the path with stripped filename extension
 */
public static String stripFilenameExtension(String path){
  if (path == null) {
    return null;
  }
  int extIndex=path.lastIndexOf(EXTENSION_SEPARATOR);
  if (extIndex == -1) {
    return path;
  }
  int folderIndex=path.lastIndexOf(FOLDER_SEPARATOR);
  if (folderIndex > extIndex) {
    return path;
  }
  return path.substring(0,extIndex);
}
