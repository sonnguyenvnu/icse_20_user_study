/** 
 * @see #copyToOutputStream(InputStream,int)
 */
public static byte[] readBytes(final InputStream input,final int count) throws IOException {
  return copyToOutputStream(input,count).toByteArray();
}
