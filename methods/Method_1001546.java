public long seed(){
  long h=1125899906842597L;
  final String string=getPlainString();
  final int len=string.length();
  for (int i=0; i < len; i++) {
    h=31 * h + string.charAt(i);
  }
  return h;
}
