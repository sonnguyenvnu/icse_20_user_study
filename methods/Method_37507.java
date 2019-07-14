/** 
 * Creates temporary directory.
 * @see #createTempFile(String,String,File)
 */
public static File createTempDirectory(final String prefix,final String suffix,final File tempDir) throws IOException {
  File file=createTempFile(prefix,suffix,tempDir);
  file.delete();
  file.mkdir();
  return file;
}
