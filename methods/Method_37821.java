private static boolean compare(final CharSequence charSequence,final CharSequence chars,final int length){
  for (int i=0; i < length; i++) {
    char c=charSequence.charAt(i);
    c=CharUtil.toLowerAscii(c);
    if (c != chars.charAt(i)) {
      return true;
    }
  }
  return false;
}
