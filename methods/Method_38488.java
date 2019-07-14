/** 
 * Calculates hash value of the input string.
 */
private int hash(final String name){
  int h=0;
  for (int i=name.length() - 1; i >= 0; i--) {
    char c=name.charAt(i);
    if (!caseSensitive) {
      if (c >= 'A' && c <= 'Z') {
        c+=32;
      }
    }
    h=31 * h + c;
  }
  if (h > 0) {
    return h;
  }
  if (h == Integer.MIN_VALUE) {
    return Integer.MAX_VALUE;
  }
  return -h;
}
