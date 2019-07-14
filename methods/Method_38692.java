/** 
 * Consumes one of the allowed char at current position. If char is different, return <code>0</code>. If matched, returns matched char.
 */
protected char consumeOneOf(final char c1,final char c2){
  char c=input[ndx];
  if ((c != c1) && (c != c2)) {
    return 0;
  }
  ndx++;
  return c;
}
