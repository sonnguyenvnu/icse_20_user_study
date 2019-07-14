/** 
 * Finds the very first next dot. Ignores dots between index brackets. Returns <code>-1</code> when dot is not found.
 */
protected int indexOfDot(final String name){
  int ndx=0;
  int len=name.length();
  boolean insideBracket=false;
  while (ndx < len) {
    char c=name.charAt(ndx);
    if (insideBracket) {
      if (c == ']') {
        insideBracket=false;
      }
    }
 else {
      if (c == '.') {
        return ndx;
      }
      if (c == '[') {
        insideBracket=true;
      }
    }
    ndx++;
  }
  return -1;
}
