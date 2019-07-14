/** 
 * ?????bit??byte
 * @param bits bits
 * @return byte
 */
public static byte bitsToByte(String bits){
  byte b=0;
  for (int i=bits.length() - 1, j=0; i >= 0; i--, j++) {
    char c=bits.charAt(i);
    if (c == '1') {
      b+=(1 << j);
    }
  }
  return b;
}
