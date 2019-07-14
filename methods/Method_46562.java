/** 
 * Locates a file on the classpath.
 * @param filename the relative filePath of the file to load
 * @return the URL of the file or null if not found
 */
public static URL locateOnClassPath(String filename){
  URL result;
  result=Thread.currentThread().getContextClassLoader().getResource(filename);
  if (result == null) {
    result=P6Util.class.getClassLoader().getResource(filename);
  }
  if (result == null) {
    result=ClassLoader.getSystemResource(filename);
  }
  return result;
}
