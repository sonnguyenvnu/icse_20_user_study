/** 
 * short???byte??
 * @param shortNumber
 * @return
 */
public static byte[] short2Byte(@NotNull short shortNumber){
  byte[] b=new byte[2];
  for (int i=0; i < 2; i++) {
    int offset=16 - (i + 1) * 8;
    b[i]=(byte)((shortNumber >> offset) & 0xff);
  }
  return b;
}
