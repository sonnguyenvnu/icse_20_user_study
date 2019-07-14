/** 
 * Retrieves the file path of this URL, with any trailing slashes removed.
 * @param url The URL to get the file path for.
 * @return The file path.
 */
public static String toFilePath(URL url){
  String filePath=new File(decodeURL(url.getPath().replace("+","%2b"))).getAbsolutePath();
  if (filePath.endsWith("/")) {
    return filePath.substring(0,filePath.length() - 1);
  }
  return filePath;
}
