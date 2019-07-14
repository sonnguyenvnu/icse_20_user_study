static public int getVoidFunctionEnd(String code,String functionName){
  List<Range> comments=getCommentBlocks(code);
  int start=getVoidFunctionStart(code,functionName);
  if (start == -1) {
    return -1;
  }
  int bracketCount=1;
  int pos=start;
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
