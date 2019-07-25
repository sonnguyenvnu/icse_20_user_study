/** 
 * Get the leafname of a path.
 * @param path the path
 * @return the string
 */
private static String leafname(final String path){
  return path.substring(path.lastIndexOf('/') + 1);
}
