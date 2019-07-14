/** 
 * @see #copy(Reader,int)
 */
public static char[] readChars(final Reader input,final int count) throws IOException {
  return copy(input,count).toCharArray();
}
