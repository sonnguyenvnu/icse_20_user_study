/** 
 * ???????????
 * @param filePath ????
 * @return filePath????
 */
public static String getDirName(String filePath){
  if (RxDataTool.isNullString(filePath)) {
    return filePath;
  }
  int lastSep=filePath.lastIndexOf(File.separator);
  return lastSep == -1 ? "" : filePath.substring(0,lastSep + 1);
}
