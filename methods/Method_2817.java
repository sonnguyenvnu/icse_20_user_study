/** 
 * ???????????
 * @param path
 * @return
 */
public static String dirname(String path){
  int index=path.lastIndexOf('/');
  if (index == -1)   return path;
  return path.substring(0,index + 1);
}
