/** 
 * Converts char array to byte array using provided encoding.  
 */
public static byte[] toByteArray(final char[] carr,final String charset){
  return StringUtil.getBytes(new String(carr),charset);
}
