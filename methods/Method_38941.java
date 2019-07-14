private boolean isAppropriateTagName(final char[] lowerCaseNameToMatch,final int from,final int to){
  final int len=to - from;
  if (len != lowerCaseNameToMatch.length) {
    return false;
  }
  for (int i=from, k=0; i < to; i++, k++) {
    char c=input[i];
    c=CharUtil.toLowerAscii(c);
    if (c != lowerCaseNameToMatch[k]) {
      return false;
    }
  }
  return true;
}
