/** 
 * Converts a string of hexadecimal characters into a byte array.
 * @param hex the hex string
 * @return the hex string decoded into a byte array
 */
private static byte[] fromHex(final String hex){
  final byte[] binary=new byte[hex.length() / 2];
  for (int i=0; i < binary.length; i++) {
    binary[i]=(byte)Integer.parseInt(hex.substring(2 * i,2 * i + 2),16);
  }
  return binary;
}
