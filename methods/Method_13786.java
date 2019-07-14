/** 
 * get a int from a byte array start from the specifield offset
 * @param b
 * @param offset
 */
public static int getInt3(byte[] b,int offset){
  return ((b[offset++] & 0x000000FF) | (b[offset++] & 0x0000FF00) | (b[offset] & 0x00FF0000));
}
