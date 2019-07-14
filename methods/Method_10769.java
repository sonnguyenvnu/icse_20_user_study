/** 
 * ???????
 * @param file ??
 * @return {@code true}: ?<br> {@code false}: ?
 */
public static boolean isDir(File file){
  return isFileExists(file) && file.isDirectory();
}
