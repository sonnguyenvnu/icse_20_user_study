/** 
 * Tests if this string starts with the specified prefix with ignored case and with the specified prefix beginning a specified index.
 * @param src        source string to test
 * @param subS       starting substring
 * @param startIndex index from where to test
 * @return <code>true</code> if the character sequence represented by the argument isa prefix of the character sequence represented by this string; <code>false</code> otherwise.
 */
public static boolean startsWithIgnoreCase(final String src,final String subS,final int startIndex){
  String sub=subS.toLowerCase();
  int sublen=sub.length();
  if (startIndex + sublen > src.length()) {
    return false;
  }
  int j=0;
  int i=startIndex;
  while (j < sublen) {
    char source=Character.toLowerCase(src.charAt(i));
    if (sub.charAt(j) != source) {
      return false;
    }
    j++;
    i++;
  }
  return true;
}
