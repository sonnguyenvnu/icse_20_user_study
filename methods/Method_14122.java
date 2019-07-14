/** 
 * Determines if one of the substrings sent in is the same as what is at the specified position in the string being encoded.
 * @param start
 * @param length
 * @param compareStrings
 * @return
 */
boolean StringAt(int start,int length,String... compareStrings){
  if ((start < 0) || (start > (m_length - 1)) || ((start + length - 1) > (m_length - 1))) {
    return false;
  }
  String target=m_inWord.substring(start,(start + length));
  for (  String strFragment : compareStrings) {
    if (target.equals(strFragment)) {
      return true;
    }
  }
  return false;
}
