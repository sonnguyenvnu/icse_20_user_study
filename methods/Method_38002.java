public static int indexOfNonWhitespace(final String string,final int startindex,final int endindex){
  for (int i=startindex; i < endindex; i++) {
    if (!CharUtil.isWhitespace(string.charAt(i))) {
      return i;
    }
  }
  return -1;
}
