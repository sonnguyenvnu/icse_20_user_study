static public int getAfterSizePos(String code){
  List<Range> comments=getCommentBlocks(code);
  Pattern p=Pattern.compile("size[\\s\\t]*\\(");
  Matcher m=p.matcher(code);
  while (m.find()) {
    if (isInRangeList(m.start(),comments) || isInRangeList(m.end(),comments)) {
      continue;
    }
    int bracketCount=1;
    int pos=m.end();
    while (bracketCount > 0 && pos < code.length()) {
      if (isInRangeList(pos,comments)) {
        pos++;
        continue;
      }
      if (code.charAt(pos) == '(') {
        bracketCount++;
      }
 else       if (code.charAt(pos) == ')') {
        bracketCount--;
      }
      pos++;
    }
    if (bracketCount != 0) {
      continue;
    }
    boolean found=false;
    while (pos < code.length()) {
      if (code.charAt(pos) == ';' && !isInRangeList(pos,comments)) {
        found=true;
        break;
      }
      pos++;
    }
    if (!found) {
      continue;
    }
    return pos + 1;
  }
  return -1;
}
