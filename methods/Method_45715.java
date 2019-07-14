/** 
 * ??byte???4bit???
 * @param b ??byte
 * @return byte?? [&lt;16,&lt;16]
 */
public static byte[] parseHigh4Low4Bytes(byte b){
  return new byte[]{(byte)((b >> 4)),(byte)((b & 0x0f))};
}
