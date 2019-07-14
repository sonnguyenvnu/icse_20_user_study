/** 
 * MD2??
 * @param data ??????
 * @return 16????
 */
public static String encryptMD2ToString(byte[] data){
  return bytes2HexString(encryptMD2(data));
}
