/** 
 * Returns  {@code true} if {@link File} exists.
 */
public static boolean isExistingFile(final File file){
  return file != null && file.exists() && file.isFile();
}
