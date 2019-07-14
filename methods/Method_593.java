public boolean scanFieldBoolean(char[] fieldName){
  matchStat=UNKNOWN;
  if (!charArrayCompare(text,bp,fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return false;
  }
  int startPos=bp;
  int index=bp + fieldName.length;
  char ch=charAt(index++);
  final boolean quote=ch == '"';
  if (quote) {
    ch=charAt(index++);
  }
  boolean value;
  if (ch == 't') {
    if (charAt(index++) != 'r') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (charAt(index++) != 'u') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (charAt(index++) != 'e') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (quote && charAt(index++) != '"') {
      matchStat=NOT_MATCH;
      return false;
    }
    bp=index;
    ch=charAt(bp);
    value=true;
  }
 else   if (ch == 'f') {
    if (charAt(index++) != 'a') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (charAt(index++) != 'l') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (charAt(index++) != 's') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (charAt(index++) != 'e') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (quote && charAt(index++) != '"') {
      matchStat=NOT_MATCH;
      return false;
    }
    bp=index;
    ch=charAt(bp);
    value=false;
  }
 else   if (ch == '1') {
    if (quote && charAt(index++) != '"') {
      matchStat=NOT_MATCH;
      return false;
    }
    bp=index;
    ch=charAt(bp);
    value=true;
  }
 else   if (ch == '0') {
    if (quote && charAt(index++) != '"') {
      matchStat=NOT_MATCH;
      return false;
    }
    bp=index;
    ch=charAt(bp);
    value=false;
  }
 else {
    matchStat=NOT_MATCH;
    return false;
  }
  for (; ; ) {
    if (ch == ',') {
      this.ch=charAt(++bp);
      matchStat=VALUE;
      token=JSONToken.COMMA;
      break;
    }
 else     if (ch == '}') {
      ch=charAt(++bp);
      for (; ; ) {
        if (ch == ',') {
          token=JSONToken.COMMA;
          this.ch=charAt(++bp);
        }
 else         if (ch == ']') {
          token=JSONToken.RBRACKET;
          this.ch=charAt(++bp);
        }
 else         if (ch == '}') {
          token=JSONToken.RBRACE;
          this.ch=charAt(++bp);
        }
 else         if (ch == EOI) {
          token=JSONToken.EOF;
        }
 else         if (isWhitespace(ch)) {
          ch=charAt(++bp);
          continue;
        }
 else {
          matchStat=NOT_MATCH;
          return false;
        }
        break;
      }
      matchStat=END;
      break;
    }
 else     if (isWhitespace(ch)) {
      ch=charAt(++bp);
    }
 else {
      bp=startPos;
      ch=charAt(bp);
      matchStat=NOT_MATCH;
      return false;
    }
  }
  return value;
}
