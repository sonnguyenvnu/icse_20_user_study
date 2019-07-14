/** 
 * Checks if byteArray interpreted as sequence of bytes starts with pattern starting at position equal to offset.
 * @param byteArray the byte array to be checked
 * @param pattern the pattern to check
 * @return true if byteArray starts with pattern
 */
public static boolean startsWithPattern(final byte[] byteArray,final byte[] pattern){
  Preconditions.checkNotNull(byteArray);
  Preconditions.checkNotNull(pattern);
  if (pattern.length > byteArray.length) {
    return false;
  }
  for (int i=0; i < pattern.length; ++i) {
    if (byteArray[i] != pattern[i]) {
      return false;
    }
  }
  return true;
}
