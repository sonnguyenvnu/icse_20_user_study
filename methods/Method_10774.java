/** 
 * ?????????filter???
 * @param dirPath     ????
 * @param filter      ???
 * @param isRecursive ????????
 * @return ????
 */
public static List<File> listFilesInDirWithFilter(String dirPath,FilenameFilter filter,boolean isRecursive){
  return listFilesInDirWithFilter(getFileByPath(dirPath),filter,isRecursive);
}
