/** 
 * ???????????????????
 * @param file ??
 * @return {@code true}: ????<br> {@code false}: ????
 */
public static boolean createFileByDeleteOldFile(File file){
  if (file == null) {
    return false;
  }
  if (file.exists() && file.isFile() && !file.delete()) {
    return false;
  }
  if (!createOrExistsDir(file.getParentFile())) {
    return false;
  }
  try {
    return file.createNewFile();
  }
 catch (  IOException e) {
    e.printStackTrace();
    return false;
  }
}
