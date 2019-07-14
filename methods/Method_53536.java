/** 
 * int?byte??
 * @param intNumber int??
 * @return byte??{@link #int2ByteArr(int)} ????????????????
 */
public static byte[] int2ByteArr2(@NotNull int intNumber){
  byte[] b=new byte[4];
  b[0]=(byte)((intNumber >> 24) & 0xFF);
  b[1]=(byte)((intNumber >> 16) & 0xFF);
  b[2]=(byte)((intNumber >> 8) & 0xFF);
  b[3]=(byte)(intNumber & 0xFF);
  return b;
}
