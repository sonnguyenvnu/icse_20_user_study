/** 
 * @see #unicodeInputStreamOf(InputStream,String)
 * @see #checkExists(File)
 * @see #checkIsFile(File)
 */
private static UnicodeInputStream unicodeInputStreamOf(final File file) throws IOException {
  checkExists(file);
  checkIsFile(file);
  return unicodeInputStreamOf(new FileInputStream(file),null);
}
