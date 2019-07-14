/** 
 * @return the last character in <tt>result</tt> not ' ' or '\n'.
 */
private char lastNonSpaceChar(){
  for (int i=result.length() - 1; i >= 0; i--) {
    char chI=result.charAt(i);
    if (chI != ' ' && chI != '\n')     return chI;
  }
  return 0;
}
