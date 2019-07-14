/** 
 * Converts all separators to the system separator.
 * @param path the path to be changed, null ignored
 * @return the updated path
 */
public static String separatorsToSystem(String path){
  if (path == null) {
    return null;
  }
  if (isSystemWindows()) {
    return separatorsToWindows(path);
  }
 else {
    return separatorsToUnix(path);
  }
}
