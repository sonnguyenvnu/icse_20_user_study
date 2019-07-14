static public int countLineSuffix(String s,String suffix){
  int count=0;
  int from=0;
  while (from < s.length()) {
    int lineEnd=s.indexOf('\n',from);
    if (lineEnd < 0) {
      break;
    }
 else {
      int i=lineEnd - 1;
      while (i >= from + suffix.length() - 1) {
        if (Character.isWhitespace(s.charAt(i))) {
          i--;
        }
 else {
          String suffix2=s.subSequence(i - suffix.length() + 1,i + 1).toString();
          if (suffix2.equals(suffix)) {
            count++;
          }
          break;
        }
      }
      from=lineEnd + 1;
    }
  }
  return count;
}
