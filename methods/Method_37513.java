/** 
 * Detect encoding on  {@link UnicodeInputStream} by using {@link UnicodeInputStream#getDetectedEncoding()}.
 * @param in {@link UnicodeInputStream}
 * @return UTF encoding as a String. If encoding could not be detected, defaults to {@link StringPool#UTF_8}.
 * @see UnicodeInputStream#getDetectedEncoding()
 */
private static String detectEncoding(final UnicodeInputStream in){
  String encoding=in.getDetectedEncoding();
  if (encoding == null) {
    encoding=StringPool.UTF_8;
  }
  return encoding;
}
