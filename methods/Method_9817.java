/** 
 * Generates upload file path for the specified file name.
 * @param fileName the specified file name
 * @return "yyyy/MM/fileName"
 */
public static String genFilePath(final String fileName){
  final String date=DateFormatUtils.format(System.currentTimeMillis(),"yyyy/MM");
  return date + "/" + fileName;
}
