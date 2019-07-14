/** 
 * Returns new  {@link UnicodeInputStream} using {@link InputStream} and targetEncoding.
 * @param input          {@link InputStream}
 * @param targetEncoding Encoding to use.
 * @return new {@link UnicodeInputStream}.
 */
private static UnicodeInputStream unicodeInputStreamOf(final InputStream input,final String targetEncoding){
  return new UnicodeInputStream(input,targetEncoding);
}
