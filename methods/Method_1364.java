/** 
 * Quickly converts a hexadecimal string to a byte array.
 */
public static byte[] decodeHex(String hexString){
  int length=hexString.length();
  if ((length & 0x01) != 0) {
    throw new IllegalArgumentException("Odd number of characters.");
  }
  boolean badHex=false;
  byte[] out=new byte[length >> 1];
  for (int i=0, j=0; j < length; i++) {
    int c1=hexString.charAt(j++);
    if (c1 > 'f') {
      badHex=true;
      break;
    }
    final byte d1=DIGITS[c1];
    if (d1 == -1) {
      badHex=true;
      break;
    }
    int c2=hexString.charAt(j++);
    if (c2 > 'f') {
      badHex=true;
      break;
    }
    final byte d2=DIGITS[c2];
    if (d2 == -1) {
      badHex=true;
      break;
    }
    out[i]=(byte)(d1 << 4 | d2);
  }
  if (badHex) {
    throw new IllegalArgumentException("Invalid hexadecimal digit: " + hexString);
  }
  return out;
}
