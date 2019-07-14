/** 
 * ??path??????path
 * @param path path
 * @return ???path
 */
static String getParentPath(String path){
  if (path == null || path.length() < 4) {
    return null;
  }
  return path.substring(0,path.length() - 5);
}
