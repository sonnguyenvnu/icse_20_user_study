/** 
 * char ??? byte[] ??
 * @param charNumber
 * @return
 */
public static byte[] char2ByteArr(@NotNull char charNumber){
  byte[] b=new byte[2];
  b[0]=(byte)((charNumber & 0xFF00) >> 8);
  b[1]=(byte)(charNumber & 0xFF);
  return b;
}
