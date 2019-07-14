/** 
 * Reads UTF file content as char array.
 * @param file {@link File} to read.
 * @return array of characters.
 * @throws IOException if something went wrong.
 */
public static char[] readUTFChars(final File file) throws IOException {
  checkExists(file);
  checkIsFile(file);
  UnicodeInputStream in=unicodeInputStreamOf(file);
  try {
    return StreamUtil.readChars(in,detectEncoding(in));
  }
  finally {
    StreamUtil.close(in);
  }
}
