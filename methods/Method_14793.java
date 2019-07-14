/** 
 * ????
 * @param path
 * @return
 */
public static String[] splitPath(String path){
  if (StringUtil.isNotEmpty(path,true) == false) {
    return null;
  }
  return isPath(path) ? split(path,SEPARATOR) : new String[]{path};
}
