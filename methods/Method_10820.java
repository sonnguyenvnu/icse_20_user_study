/** 
 * ??????????????
 * @param filePath ?????
 * @return {@code true}: ?<br> {@code false}: ?
 */
public static boolean isImage(String filePath){
  String path=filePath.toUpperCase();
  return path.endsWith(".PNG") || path.endsWith(".JPG") || path.endsWith(".JPEG") || path.endsWith(".BMP") || path.endsWith(".GIF");
}
