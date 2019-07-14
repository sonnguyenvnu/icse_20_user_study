/** 
 * Writes unicode representation of a character.
 */
protected void unicode(final char c){
  write("\\u");
  int n=c;
  for (int i=0; i < 4; ++i) {
    int digit=(n & 0xf000) >> 12;
    char hex=CharUtil.int2hex(digit);
    write(hex);
    n<<=4;
  }
}
