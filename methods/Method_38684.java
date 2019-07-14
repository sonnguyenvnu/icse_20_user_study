/** 
 * Skips over complete object. It is not parsed, just skipped. It will be parsed later, but only if required.
 */
private void skipObject(){
  int bracketCount=1;
  boolean insideString=false;
  while (ndx < total) {
    final char c=input[ndx];
    if (insideString) {
      if (c == '\"' && notPrecededByEvenNumberOfBackslashes()) {
        insideString=false;
      }
    }
 else     if (c == '\"') {
      insideString=true;
    }
 else     if (c == '{') {
      bracketCount++;
    }
 else     if (c == '}') {
      bracketCount--;
      if (bracketCount == 0) {
        ndx++;
        return;
      }
    }
    ndx++;
  }
}
