/** 
 * Finds index of the string. Returns -1 if the string was not found.
 */
public int find(String string){
  if (string == null) {
    return -1;
  }
  for (int i=0; i != m_stringOffsets.length; ++i) {
    int offset=m_stringOffsets[i];
    int length=getShort(m_strings,offset);
    if (length != string.length()) {
      continue;
    }
    int j=0;
    for (; j != length; ++j) {
      offset+=2;
      if (string.charAt(j) != getShort(m_strings,offset)) {
        break;
      }
    }
    if (j == length) {
      return i;
    }
  }
  return -1;
}
