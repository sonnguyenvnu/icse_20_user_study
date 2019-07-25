private static int _valid(String s,Type t,boolean template){
  boolean[] table=ENCODING_TABLES[t.ordinal()];
  for (int i=0; i < s.length(); i++) {
    final char c=s.charAt(i);
    if ((c < 0x80 && c != '%' && !table[c]) || c >= 0x80) {
      if (!template || (c != '{' && c != '}')) {
        return i;
      }
    }
  }
  return -1;
}
