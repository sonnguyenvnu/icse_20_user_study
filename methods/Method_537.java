public BigDecimal scanDecimal(char seperator){
  matchStat=UNKNOWN;
  int offset=0;
  char chLocal=charAt(bp + (offset++));
  final boolean quote=chLocal == '"';
  if (quote) {
    chLocal=charAt(bp + (offset++));
  }
  boolean negative=chLocal == '-';
  if (negative) {
    chLocal=charAt(bp + (offset++));
  }
  BigDecimal value;
  if (chLocal >= '0' && chLocal <= '9') {
    for (; ; ) {
      chLocal=charAt(bp + (offset++));
      if (chLocal >= '0' && chLocal <= '9') {
        continue;
      }
 else {
        break;
      }
    }
    boolean small=(chLocal == '.');
    if (small) {
      chLocal=charAt(bp + (offset++));
      if (chLocal >= '0' && chLocal <= '9') {
        for (; ; ) {
          chLocal=charAt(bp + (offset++));
          if (chLocal >= '0' && chLocal <= '9') {
            continue;
          }
 else {
            break;
          }
        }
      }
 else {
        matchStat=NOT_MATCH;
        return null;
      }
    }
    boolean exp=chLocal == 'e' || chLocal == 'E';
    if (exp) {
      chLocal=charAt(bp + (offset++));
      if (chLocal == '+' || chLocal == '-') {
        chLocal=charAt(bp + (offset++));
      }
      for (; ; ) {
        if (chLocal >= '0' && chLocal <= '9') {
          chLocal=charAt(bp + (offset++));
        }
 else {
          break;
        }
      }
    }
    int start, count;
    if (quote) {
      if (chLocal != '"') {
        matchStat=NOT_MATCH;
        return null;
      }
 else {
        chLocal=charAt(bp + (offset++));
      }
      start=bp + 1;
      count=bp + offset - start - 2;
    }
 else {
      start=bp;
      count=bp + offset - start - 1;
    }
    char[] chars=this.sub_chars(start,count);
    value=new BigDecimal(chars);
  }
 else   if (chLocal == 'n' && charAt(bp + offset) == 'u' && charAt(bp + offset + 1) == 'l' && charAt(bp + offset + 2) == 'l') {
    matchStat=VALUE_NULL;
    value=null;
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
 else       if (chLocal == '}') {
        bp+=offset;
        this.ch=charAt(bp);
        matchStat=VALUE_NULL;
        token=JSONToken.RBRACE;
        return value;
      }
 else       if (isWhitespace(chLocal)) {
        chLocal=charAt(bp + offset++);
        continue;
      }
      break;
    }
    matchStat=NOT_MATCH;
    return null;
  }
 else {
    matchStat=NOT_MATCH;
    return null;
  }
  if (chLocal == ',') {
    bp+=offset;
    this.ch=this.charAt(bp);
    matchStat=VALUE;
    token=JSONToken.COMMA;
    return value;
  }
  if (chLocal == ']') {
    chLocal=charAt(bp + (offset++));
    if (chLocal == ',') {
      token=JSONToken.COMMA;
      bp+=offset;
      this.ch=this.charAt(bp);
    }
 else     if (chLocal == ']') {
      token=JSONToken.RBRACKET;
      bp+=offset;
      this.ch=this.charAt(bp);
    }
 else     if (chLocal == '}') {
      token=JSONToken.RBRACE;
      bp+=offset;
      this.ch=this.charAt(bp);
    }
 else     if (chLocal == EOI) {
      token=JSONToken.EOF;
      bp+=(offset - 1);
      ch=EOI;
    }
 else {
      matchStat=NOT_MATCH;
      return null;
    }
    matchStat=END;
  }
 else {
    matchStat=NOT_MATCH;
    return null;
  }
  return value;
}
