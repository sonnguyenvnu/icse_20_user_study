/** 
 * Checks whether the Dex file fits to the class names. Assumes that the file has been created with this class.
 */
public static boolean matches(byte[] dex,String childClz,String superClz) throws IOException {
  boolean childFirst=childClz.compareTo(superClz) < 0;
  byte[] childBytes=stringToBytes("L" + childClz.replace('.','/') + ";");
  byte[] superBytes=stringToBytes("L" + superClz.replace('.','/') + ";");
  int pos=0xa0;
  if (pos + childBytes.length + superBytes.length >= dex.length) {
    return false;
  }
  for (  byte b : childFirst ? childBytes : superBytes) {
    if (dex[pos++] != b) {
      return false;
    }
  }
  for (  byte b : childFirst ? superBytes : childBytes) {
    if (dex[pos++] != b) {
      return false;
    }
  }
  return true;
}
