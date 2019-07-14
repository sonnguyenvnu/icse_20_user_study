/** 
 * @see #copyToOutputStream(Reader,String,int)
 */
public static byte[] readBytes(final Reader input,final String encoding,final int count) throws IOException {
  return copyToOutputStream(input,encoding,count).toByteArray();
}
