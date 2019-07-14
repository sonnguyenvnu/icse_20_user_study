/** 
 * ?????????????
 * @param b
 * @return
 */
public static int getUnsigned(byte b){
  if (b > 0)   return (int)b;
 else   return (b & 0x7F + 128);
}
