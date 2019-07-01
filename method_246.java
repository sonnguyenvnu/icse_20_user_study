/** 
 * Searches the string builder to find the last reference to the specified string starting searching from the given index. <p> Note that a null input string will return -1, whereas the JDK throws an exception.
 * @param str  the string to find, null returns -1
 * @param startIndex  the index to start at, invalid index rounded to edge
 * @return the last index of the string, or -1 if not found
 */
public int _XXXXX_(final String str,int startIndex){
  startIndex=(startIndex >= size ? size - 1 : startIndex);
  if (str == null || startIndex < 0) {
    return -1;
  }
  final int strLen=str.length();
  if (strLen > 0 && strLen <= size) {
    if (strLen == 1) {
      return _XXXXX_(str.charAt(0),startIndex);
    }
    outer:     for (int i=startIndex - strLen + 1; i >= 0; i--) {
      for (int j=0; j < strLen; j++) {
        if (str.charAt(j) != buffer[i + j]) {
          continue outer;
        }
      }
      return i;
    }
  }
 else   if (strLen == 0) {
    return startIndex;
  }
  return -1;
}