/** 
 * get unique hash according to file content and filename
 */
@NotNull public static String getFileHash(@NotNull String path){
  byte[] bytes=getBytesFromFile(path);
  return _.getContentHash(path.getBytes()) + "." + getContentHash(bytes);
}
