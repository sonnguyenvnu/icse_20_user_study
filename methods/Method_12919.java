/** 
 * @param directory
 */
private static synchronized void syncCreatePOIFilesDirectory(File directory){
  if (!directory.exists()) {
    directory.mkdirs();
  }
}
