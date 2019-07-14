/** 
 * @see #readChars(File,String)
 */
public static char[] readChars(final File file) throws IOException {
  return readChars(file,encoding());
}
