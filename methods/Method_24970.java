static public int getSetupEnd(String code){
  List<Range> comments=getCommentBlocks(code);
  int setupStart=getSetupStart(code);
  if (setupStart == -1) {
    return -1;
  }
  int bracketCount=1;
  int pos=setupStart;
  while (bracketCount > 0 && pos < code.length()) {
    if (isInRangeList(pos,comments)) {
      pos++;
      continue;
    }
    if (code.charAt(pos) == '{') {
      bracketCount++;
    }
 else     if (code.charAt(pos) == '}') {
      bracketCount--;
    }
    pos++;
  }
  if (bracketCount == 0) {
    return pos - 1;
  }
  return -1;
}
