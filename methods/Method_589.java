public long scanFieldSymbol(char[] fieldName){
  matchStat=UNKNOWN;
  if (!charArrayCompare(text,bp,fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return 0;
  }
  int index=bp + fieldName.length;
  char ch=charAt(index++);
  if (ch != '"') {
    matchStat=NOT_MATCH;
    return 0;
  }
  long hash=0xcbf29ce484222325L;
  for (; ; ) {
    ch=charAt(index++);
    if (ch == '\"') {
      bp=index;
      this.ch=ch=charAt(bp);
      break;
    }
 else     if (index > len) {
      matchStat=NOT_MATCH;
      return 0;
    }
    hash^=ch;
    hash*=0x100000001b3L;
  }
  for (; ; ) {
    if (ch == ',') {
      this.ch=charAt(++bp);
      matchStat=VALUE;
      return hash;
    }
 else     if (ch == '}') {
      next();
      skipWhitespace();
      ch=getCurrent();
      if (ch == ',') {
        token=JSONToken.COMMA;
        this.ch=charAt(++bp);
      }
 else       if (ch == ']') {
        token=JSONToken.RBRACKET;
        this.ch=charAt(++bp);
      }
 else       if (ch == '}') {
        token=JSONToken.RBRACE;
        this.ch=charAt(++bp);
      }
 else       if (ch == EOI) {
        token=JSONToken.EOF;
      }
 else {
        matchStat=NOT_MATCH;
        return 0;
      }
      matchStat=END;
      break;
    }
 else     if (isWhitespace(ch)) {
      ch=charAt(++bp);
      continue;
    }
 else {
      matchStat=NOT_MATCH;
      return 0;
    }
  }
  return hash;
}
