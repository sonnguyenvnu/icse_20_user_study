/** 
 * Tests if this string ends with the specified suffix.
 * @param src    String to test
 * @param subS   suffix
 * @return <code>true</code> if the character sequence represented by the argument isa suffix of the character sequence represented by this object; <code>false</code> otherwise.
 */
public static boolean endsWithIgnoreCase(final String src,final String subS){
  String sub=subS.toLowerCase();
  int sublen=sub.length();
  int j=0;
  int i=src.length() - sublen;
  if (i < 0) {
    return false;
  }
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
