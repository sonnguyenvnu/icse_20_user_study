/** 
 * ??byte???2bit?6bit???
 * @param b ??byte
 * @return byte??{&lt;4,&lt;64}
 */
public static byte[] parseHigh2Low6Bytes(byte b){
  return new byte[]{(byte)((b >> 6)),(byte)((b & 0x3f))};
}
