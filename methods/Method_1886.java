/** 
 * Compares some bytes with the text we're expecting
 * @param what The bytes to compare
 * @param with The string those bytes should contains
 * @return True if they match and false otherwise
 */
private static boolean compare(byte[] what,String with){
  if (what.length != with.length()) {
    return false;
  }
  for (int i=0; i < what.length; i++) {
    if (with.charAt(i) != what[i]) {
      return false;
    }
  }
  return true;
}
