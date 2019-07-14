/** 
 * ????????????????
 * @param charArr ????char[]
 * @return byte[] ????
 * @throws RuntimeException ????????????????????????????
 */
public static byte[] charArrHex2ByteArr(@NotNull char[] charArr){
  int len=charArr.length;
  if ((len & 0x01) != 0) {
    throw new RuntimeException("Odd number of characters.");
  }
  byte[] out=new byte[len >> 1];
  for (int i=0, j=0; j < len; i++) {
    int f=charHex2Int(charArr[j],j) << 4;
    j++;
    f=f | charHex2Int(charArr[j],j);
    j++;
    out[i]=(byte)(f & 0xFF);
  }
  return out;
}
