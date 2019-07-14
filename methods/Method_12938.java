/** 
 * Apply the given relative path to the given Java resource path, assuming standard Java folder separation (i.e. "/" separators).
 * @param path the path to start from (usually a full file path)
 * @param relativePath the relative path to apply(relative to the full file path above)
 * @return the full file path that results from applying the relative path
 */
public static String applyRelativePath(String path,String relativePath){
  int separatorIndex=path.lastIndexOf(FOLDER_SEPARATOR);
  if (separatorIndex != -1) {
    String newPath=path.substring(0,separatorIndex);
    if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
      newPath+=FOLDER_SEPARATOR;
    }
    return newPath + relativePath;
  }
 else {
    return relativePath;
  }
}
