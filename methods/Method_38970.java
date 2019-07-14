/** 
 * Matches char buffer given in uppercase with content at current location, that will be converted to upper case to make case-insensitive matching.
 */
public final boolean matchUpperCase(final char[] uppercaseTarget){
  if (ndx + uppercaseTarget.length > total) {
    return false;
  }
  int j=ndx;
  for (int i=0; i < uppercaseTarget.length; i++, j++) {
    final char c=CharUtil.toUpperAscii(input[j]);
    if (c != uppercaseTarget[i]) {
      return false;
    }
  }
  return true;
}
