/** 
 * Reads file content as char array.
 * @param file     {@link File} to read.
 * @param encoding Encoding to use.
 * @return array of characters.
 * @throws IOException if something went wrong.
 */
public static char[] readChars(final File file,final String encoding) throws IOException {
  checkExists(file);
  checkIsFile(file);
  InputStream in=streamOf(file,encoding);
  try {
    return StreamUtil.readChars(in,encoding);
  }
  finally {
    StreamUtil.close(in);
  }
}
