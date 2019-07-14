/** 
 * @see #outputStreamWriterOf(OutputStream,String)
 */
public static OutputStreamWriter outputStreamWriterOf(final OutputStream output) throws UnsupportedEncodingException {
  return outputStreamWriterOf(output,encoding());
}
