public long scanLong(char expectNextChar){
  matchStat=UNKNOWN;
  int offset=0;
  char chLocal=charAt(bp + (offset++));
  final boolean quote=chLocal == '"';
  if (quote) {
    chLocal=charAt(bp + (offset++));
  }
  final boolean negative=chLocal == '-';
  if (negative) {
    chLocal=charAt(bp + (offset++));
  }
  long value;
  if (chLocal >= '0' && chLocal <= '9') {
    value=chLocal - '0';
    for (; ; ) {
      chLocal=charAt(bp + (offset++));
      if (chLocal >= '0' && chLocal <= '9') {
        value=value * 10 + (chLocal - '0');
      }
 else       if (chLocal == '.') {
        matchStat=NOT_MATCH;
        return 0;
      }
 else {
        break;
      }
    }
    boolean valid=value >= 0 || (value == -9223372036854775808L && negative);
    if (!valid) {
      String val=subString(bp,offset - 1);
      throw new NumberFormatException(val);
    }
  }
 else   if (chLocal == 'n' && charAt(bp + offset) == 'u' && charAt(bp + offset + 1) == 'l' && charAt(bp + offset + 2) == 'l') {
    matchStat=VALUE_NULL;
    value=0;
    offset+=3;
    chLocal=charAt(bp + offset++);
    if (quote && chLocal == '"') {
      chLocal=charAt(bp + offset++);
    }
    for (; ; ) {
      if (chLocal == ',') {
        bp+=offset;
        this.ch=charAt(bp);
        matchStat=VALUE_NULL;
        token=JSONToken.COMMA;
        return value;
      }
 else       if (chLocal == ']') {
        bp+=offset;
        this.ch=charAt(bp);
        matchStat=VALUE_NULL;
        token=JSONToken.RBRACKET;
        return value;
      }
 else       if (isWhitespace(chLocal)) {
        chLocal=charAt(bp + offset++);
        continue;
      }
      break;
    }
    matchStat=NOT_MATCH;
    return 0;
  }
 else {
    matchStat=NOT_MATCH;
    return 0;
  }
  if (quote) {
    if (chLocal != '"') {
      matchStat=NOT_MATCH;
      return 0;
    }
 else {
      chLocal=charAt(bp + (offset++));
    }
  }
  for (; ; ) {
    if (chLocal == expectNextChar) {
      bp+=offset;
      this.ch=this.charAt(bp);
      matchStat=VALUE;
      token=JSONToken.COMMA;
      return negative ? -value : value;
    }
 else {
      if (isWhitespace(chLocal)) {
        chLocal=charAt(bp + (offset++));
        continue;
      }
      matchStat=NOT_MATCH;
      return value;
    }
  }
}
