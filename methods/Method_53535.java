/** 
 * int?byte??
 * @param intNumber int??
 * @return byte??<p> {@link #int2ByteArr2(int)} ????????????????</p>
 */
public static byte[] int2ByteArr(@NotNull int intNumber){
  byte[] b=new byte[4];
  b[0]=(byte)(intNumber & 0xff);
  b[1]=(byte)((intNumber >> 8) & 0xff);
  b[2]=(byte)((intNumber >> 16) & 0xff);
  b[3]=(byte)((intNumber >> 24) & 0xff);
  return b;
}
