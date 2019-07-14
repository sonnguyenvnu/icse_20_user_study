/** 
 * Check if a file is readable.
 * @param file The file
 * @return true if the file is reabable.
 */
public static boolean isReadable(final File file){
  if (file == null)   return false;
  if (!file.exists())   return false;
  boolean result;
  try {
    result=file.canRead();
  }
 catch (  SecurityException e) {
    return false;
  }
  return result;
}
