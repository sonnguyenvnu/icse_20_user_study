/** 
 * ??????????
 * @param filePath ????
 * @return ????
 */
public static String getFileCharsetSimple(String filePath){
  return getFileCharsetSimple(getFileByPath(filePath));
}
