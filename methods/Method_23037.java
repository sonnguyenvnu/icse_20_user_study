/** 
 * Remove all files in a directory and the directory itself. Optinally prints error messages with failed filenames. Does not follow symlinks.
 */
static public boolean removeDir(File dir,boolean printErrorMessages){
  if (!dir.exists())   return true;
  boolean result=true;
  if (!Files.isSymbolicLink(dir.toPath())) {
    File[] files=dir.listFiles();
    if (files != null) {
      for (      File child : files) {
        if (child.isFile()) {
          boolean deleted=child.delete();
          if (!deleted && printErrorMessages) {
            System.err.println("Could not delete " + child.getAbsolutePath());
          }
          result&=deleted;
        }
 else         if (child.isDirectory()) {
          result&=removeDir(child,printErrorMessages);
        }
      }
    }
  }
  boolean deleted=dir.delete();
  if (!deleted && printErrorMessages) {
    System.err.println("Could not delete " + dir.getAbsolutePath());
  }
  result&=deleted;
  return result;
}
