/** 
 * ????????????
 * @param filePath ????
 * @return ?????
 */
public static String getFileExtension(String filePath){
  if (RxDataTool.isNullString(filePath)) {
    return filePath;
  }
  int lastPoi=filePath.lastIndexOf('.');
  int lastSep=filePath.lastIndexOf(File.separator);
  if (lastPoi == -1 || lastSep >= lastPoi) {
    return "";
  }
  return filePath.substring(lastPoi);
}
