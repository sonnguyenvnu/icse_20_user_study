/** 
 * Check if a file is writable. Detects write issues on external SD card.
 * @param file The file
 * @return true if the file is writable.
 */
public static boolean isWritable(final File file){
  if (file == null)   return false;
  boolean isExisting=file.exists();
  try {
    FileOutputStream output=new FileOutputStream(file,true);
    try {
      output.close();
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
 catch (  FileNotFoundException e) {
    e.printStackTrace();
    return false;
  }
  boolean result=file.canWrite();
  if (!isExisting) {
    file.delete();
  }
  return result;
}
