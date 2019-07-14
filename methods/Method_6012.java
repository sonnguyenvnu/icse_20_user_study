/** 
 * Returns a byte array containing values parsed from the hex string provided.
 * @param hexString The hex string to convert to bytes.
 * @return A byte array containing values parsed from the hex string provided.
 */
public static byte[] getBytesFromHexString(String hexString){
  byte[] data=new byte[hexString.length() / 2];
  for (int i=0; i < data.length; i++) {
    int stringOffset=i * 2;
    data[i]=(byte)((Character.digit(hexString.charAt(stringOffset),16) << 4) + Character.digit(hexString.charAt(stringOffset + 1),16));
  }
  return data;
}
