private static char[] asUnicodeHexEscape(char c){
  char[] r=new char[6];
  r[0]='\\';
  r[1]='u';
  r[5]=HEX_DIGITS[c & 0xF];
  c>>>=4;
  r[4]=HEX_DIGITS[c & 0xF];
  c>>>=4;
  r[3]=HEX_DIGITS[c & 0xF];
  c>>>=4;
  r[2]=HEX_DIGITS[c & 0xF];
  return r;
}
