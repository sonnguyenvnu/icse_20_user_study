private boolean matchTagName(final char[] tagNameLowercase){
  final CharSequence charSequence=tag.getName();
  int length=tagNameLowercase.length;
  if (charSequence.length() != length) {
    return false;
  }
  for (int i=0; i < length; i++) {
    char c=charSequence.charAt(i);
    c=CharUtil.toLowerAscii(c);
    if (c != tagNameLowercase[i]) {
      return false;
    }
  }
  return true;
}
