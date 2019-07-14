/** 
 * Matches char buffer with content on given location.
 */
protected final boolean match(final char[] target){
  for (  char c : target) {
    if (input[ndx] != c) {
      return false;
    }
    ndx++;
  }
  return true;
}
