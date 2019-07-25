/** 
 * Gets the unicode of a string
 * @param str string to convert to unicode
 * @return unicode of string
 */
private static byte[] unicode(final String str){
  return (null != str) ? str.getBytes(java.nio.charset.StandardCharsets.UTF_16LE) : null;
}
