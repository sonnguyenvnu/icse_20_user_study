/** 
 * Helper method for  {@link #getMimeType(String,boolean)}to calculate the last '.' extension of files
 * @param path the path of file
 * @return extension extracted from name in lowercase
 */
public static String getExtension(String path){
  if (path.contains("."))   return path.substring(path.lastIndexOf(".") + 1).toLowerCase();
 else   return "";
}
