/** 
 * ??????
 * @param filePath ????
 * @return ????
 */
public static String getImageType(String filePath){
  return getImageType(RxFileTool.getFileByPath(filePath));
}
