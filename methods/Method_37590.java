/** 
 * Returns new  {@link InputStreamReader} using specified {@link InputStream} and encoding.
 * @param input    {@link InputStream}
 * @param encoding Encoding as {@link String} to use for {@link InputStreamReader}.
 * @return new {@link InputStreamReader}
 * @throws UnsupportedEncodingException if encoding is not valid.
 */
public static InputStreamReader inputStreamReadeOf(final InputStream input,final String encoding) throws UnsupportedEncodingException {
  return new InputStreamReader(input,encoding);
}
