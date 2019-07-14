/** 
 * Returns new  {@link OutputStreamWriter} using specified {@link OutputStream} and encoding.
 * @param output   {@link OutputStream}
 * @param encoding Encoding as {@link String} to use for {@link OutputStreamWriter}.
 * @return new {@link OutputStreamWriter}
 * @throws UnsupportedEncodingException if encoding is not valid.
 */
public static OutputStreamWriter outputStreamWriterOf(final OutputStream output,final String encoding) throws UnsupportedEncodingException {
  return new OutputStreamWriter(output,encoding);
}
