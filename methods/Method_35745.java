public static String wrapIfLongestLineExceedsLimit(String s,int maxLineLength){
  int longestLength=findLongestLineLength(s);
  if (longestLength > maxLineLength) {
    String wrapped=WordUtils.wrap(s,maxLineLength,null,true);
    return wrapped.replaceAll("(?m)^[ \t]*\r?\n","");
  }
  return s;
}
