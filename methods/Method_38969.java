/** 
 * Matches char buffer with content on given location.
 */
protected final boolean match(final char[] target,final int ndx){
  if (ndx + target.length >= total) {
    return false;
  }
  int j=ndx;
  for (int i=0; i < target.length; i++, j++) {
    if (input[j] != target[i]) {
      return false;
    }
  }
  return true;
}
