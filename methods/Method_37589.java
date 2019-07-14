/** 
 * @see #inputStreamReadeOf(InputStream,String)
 */
public static InputStreamReader inputStreamReadeOf(final InputStream input) throws UnsupportedEncodingException {
  return inputStreamReadeOf(input,encoding());
}
