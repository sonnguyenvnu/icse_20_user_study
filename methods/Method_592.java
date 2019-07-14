public long scanFieldLong(char[] fieldName){
  matchStat=UNKNOWN;
  int startPos=this.bp;
  char startChar=this.ch;
  if (!charArrayCompare(text,bp,fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return 0;
  }
  int index=bp + fieldName.length;
  char ch=charAt(index++);
  final boolean quote=ch == '"';
  if (quote) {
    ch=charAt(index++);
  }
  boolean negative=false;
  if (ch == '-') {
    ch=charAt(index++);
    negative=true;
  }
  long value;
  if (ch >= '0' && ch <= '9') {
    value=ch - '0';
    for (; ; ) {
      ch=charAt(index++);
      if (ch >= '0' && ch <= '9') {
        value=value * 10 + (ch - '0');
      }
 else       if (ch == '.') {
        matchStat=NOT_MATCH;
        return 0;
      }
 else {
        if (quote) {
          if (ch != '"') {
            matchStat=NOT_MATCH;
            return 0;
          }
 else {
            ch=charAt(index++);
          }
        }
        if (ch == ',' || ch == '}') {
          bp=index - 1;
        }
        break;
      }
    }
    boolean valid=value >= 0 || (value == -9223372036854775808L && negative);
    if (!valid) {
      this.bp=startPos;
      this.ch=startChar;
      matchStat=NOT_MATCH;
      return 0;
    }
  }
 else {
    this.bp=startPos;
    this.ch=startChar;
    matchStat=NOT_MATCH;
    return 0;
  }
  for (; ; ) {
    if (ch == ',') {
      this.ch=charAt(++bp);
      matchStat=VALUE;
      token=JSONToken.COMMA;
      return negative ? -value : value;
    }
 else     if (ch == '}') {
      ch=charAt(++bp);
      for (; ; ) {
        if (ch == ',') {
          token=JSONToken.COMMA;
          this.ch=charAt(++bp);
          break;
        }
 else         if (ch == ']') {
          token=JSONToken.RBRACKET;
          this.ch=charAt(++bp);
          break;
        }
 else         if (ch == '}') {
          token=JSONToken.RBRACE;
          this.ch=charAt(++bp);
          break;
        }
 else         if (ch == EOI) {
          token=JSONToken.EOF;
          break;
        }
 else         if (isWhitespace(ch)) {
          ch=charAt(++bp);
        }
 else {
          this.bp=startPos;
          this.ch=startChar;
          matchStat=NOT_MATCH;
          return 0;
        }
      }
      matchStat=END;
      break;
    }
 else     if (isWhitespace(ch)) {
      bp=index;
      ch=charAt(index++);
      continue;
    }
 else {
      matchStat=NOT_MATCH;
      return 0;
    }
  }
  return negative ? -value : value;
}
