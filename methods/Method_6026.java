/** 
 * Creates an empty directory in the directory returned by  {@link Context#getCacheDir()}. 
 */
public static File createTempDirectory(Context context,String prefix) throws IOException {
  File tempFile=createTempFile(context,prefix);
  tempFile.delete();
  tempFile.mkdir();
  return tempFile;
}
