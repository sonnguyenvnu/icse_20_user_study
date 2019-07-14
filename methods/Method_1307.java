/** 
 * Deletes all files and subdirectories in directory (doesn't delete the directory passed as parameter).
 */
public static boolean deleteContents(File directory){
  File[] files=directory.listFiles();
  boolean success=true;
  if (files != null) {
    for (    File file : files) {
      success&=deleteRecursively(file);
    }
  }
  return success;
}
