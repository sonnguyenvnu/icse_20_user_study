/** 
 * ????????????
 * @param filePath
 * @return
 */
public static String getPath(String filePath){
  String path="";
  if (StringUtils.isNotBlank(filePath)) {
    path=filePath.substring(0,filePath.lastIndexOf("/") + 1);
  }
  return path;
}
