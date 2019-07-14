public int scanFieldInt(char[] fieldName){
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
  final boolean negative=ch == '-';
  if (negative) {
    ch=charAt(index++);
  }
  int value;
  if (ch >= '0' && ch <= '9') {
    value=ch - '0';
    for (; ; ) {
      ch=charAt(index++);
      if (ch >= '0' && ch <= '9') {
        int value_10=value * 10;
        if (value_10 < value) {
          matchStat=NOT_MATCH;
          return 0;
        }
        value=value_10 + (ch - '0');
      }
 else       if (ch == '.') {
        matchStat=NOT_MATCH;
        return 0;
      }
 else {
        break;
      }
    }
    if (value < 0) {
      matchStat=NOT_MATCH;
      return 0;
    }
    if (quote) {
      if (ch != '"') {
        matchStat=NOT_MATCH;
        return 0;
      }
 else {
        ch=charAt(index++);
      }
    }
    for (; ; ) {
      if (ch == ',' || ch == '}') {
        bp=index - 1;
        break;
      }
 else       if (isWhitespace(ch)) {
        ch=charAt(index++);
        continue;
      }
 else {
        matchStat=NOT_MATCH;
        return 0;
      }
    }
  }
 else {
    matchStat=NOT_MATCH;
    return 0;
  }
  if (ch == ',') {
    this.ch=charAt(++bp);
    matchStat=VALUE;
    token=JSONToken.COMMA;
    return negative ? -value : value;
  }
  if (ch == '}') {
    bp=index - 1;
    ch=charAt(++bp);
    for (; ; ) {
      if (ch == ',') {
        token=JSONToken.COMMA;
        this.ch=charAt(++bp);
        break;
      }
 else       if (ch == ']') {
        token=JSONToken.RBRACKET;
        this.ch=charAt(++bp);
        break;
      }
 else       if (ch == '}') {
        token=JSONToken.RBRACE;
        this.ch=charAt(++bp);
        break;
      }
 else       if (ch == EOI) {
        token=JSONToken.EOF;
        break;
      }
 else       if (isWhitespace(ch)) {
        ch=charAt(++bp);
        continue;
      }
 else {
        this.bp=startPos;
        this.ch=startChar;
        matchStat=NOT_MATCH;
        return 0;
      }
    }
    matchStat=END;
  }
  return negative ? -value : value;
}
