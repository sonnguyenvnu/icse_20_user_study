public boolean matchField2(char[] fieldName){
  while (isWhitespace(ch)) {
    next();
  }
  if (!charArrayCompare(fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return false;
  }
  int offset=bp + fieldName.length;
  char ch=text.charAt(offset++);
  while (isWhitespace(ch)) {
    ch=text.charAt(offset++);
  }
  if (ch == ':') {
    this.bp=offset;
    this.ch=charAt(bp);
    return true;
  }
 else {
    matchStat=NOT_MATCH_NAME;
    return false;
  }
}
