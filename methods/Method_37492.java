/** 
 * @see #readChars(File,String)
 */
public static char[] readChars(final String fileName,final String encoding) throws IOException {
  return readChars(file(fileName),encoding);
}
