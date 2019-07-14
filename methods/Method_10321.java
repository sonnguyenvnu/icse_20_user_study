/** 
 * Converts hex values from strings to byte arra
 * @param hexString string of hex-encoded values
 * @return decoded byte array
 */
protected byte[] hexStringToByteArray(String hexString){
  int len=hexString.length();
  byte[] data=new byte[len / 2];
  for (int i=0; i < len; i+=2) {
    data[i / 2]=(byte)((Character.digit(hexString.charAt(i),16) << 4) + Character.digit(hexString.charAt(i + 1),16));
  }
  return data;
}
