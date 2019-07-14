/** 
 * Checks if byteArray interpreted as sequence of bytes contains the pattern.
 * @param byteArray the byte array to be checked
 * @param pattern the pattern to check
 * @return index of beginning of pattern, if found; otherwise -1
 */
public static int indexOfPattern(final byte[] byteArray,final int byteArrayLen,final byte[] pattern,final int patternLen){
  Preconditions.checkNotNull(byteArray);
  Preconditions.checkNotNull(pattern);
  if (patternLen > byteArrayLen) {
    return -1;
  }
  byte first=pattern[0];
  int max=byteArrayLen - patternLen;
  for (int i=0; i <= max; i++) {
    if (byteArray[i] != first) {
      while (++i <= max && byteArray[i] != first) {
      }
    }
    if (i <= max) {
      int j=i + 1;
      int end=j + patternLen - 1;
      for (int k=1; j < end && byteArray[j] == pattern[k]; j++, k++) {
      }
      if (j == end) {
        return i;
      }
    }
  }
  return -1;
}
