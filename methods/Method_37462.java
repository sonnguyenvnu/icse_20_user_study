/** 
 * Converts all separators to the Unix separator of forward slash.
 * @param path  the path to be changed, null ignored
 * @return the updated path
 */
public static String separatorsToUnix(final String path){
  if (path == null || path.indexOf(WINDOWS_SEPARATOR) == -1) {
    return path;
  }
  return path.replace(WINDOWS_SEPARATOR,UNIX_SEPARATOR);
}
