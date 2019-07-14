/** 
 * byteArr?hexString <p>???</p> bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns 00A8
 * @param bytes byte??
 * @return 16???????
 */
public static String bytes2HexString(byte[] bytes){
  char[] ret=new char[bytes.length << 1];
  for (int i=0, j=0; i < bytes.length; i++) {
    ret[j++]=HEX_DIGITS[bytes[i] >>> 4 & 0x0f];
    ret[j++]=HEX_DIGITS[bytes[i] & 0x0f];
  }
  return new String(ret);
}
