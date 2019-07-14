/** 
 * ?byte??????bit
 * @param b byte
 * @return bit???
 */
public static String byteToBits(byte b){
  return "" + (byte)((b >> 7) & 0x01) + (byte)((b >> 6) & 0x1) + (byte)((b >> 5) & 0x01) + (byte)((b >> 4) & 0x1) + (byte)((b >> 3) & 0x01) + (byte)((b >> 2) & 0x1) + (byte)((b >> 1) & 0x01) + (byte)((b >> 0) & 0x1);
}
