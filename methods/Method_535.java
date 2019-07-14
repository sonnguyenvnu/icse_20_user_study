public final float scanFloat(char seperator){
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
  float value;
  if (chLocal >= '0' && chLocal <= '9') {
    long intVal=chLocal - '0';
    for (; ; ) {
      chLocal=charAt(bp + (offset++));
      if (chLocal >= '0' && chLocal <= '9') {
        intVal=intVal * 10 + (chLocal - '0');
        continue;
      }
 else {
        break;
      }
    }
    long power=1;
    boolean small=(chLocal == '.');
    if (small) {
      chLocal=charAt(bp + (offset++));
      if (chLocal >= '0' && chLocal <= '9') {
        intVal=intVal * 10 + (chLocal - '0');
        power=10;
        for (; ; ) {
          chLocal=charAt(bp + (offset++));
          if (chLocal >= '0' && chLocal <= '9') {
            intVal=intVal * 10 + (chLocal - '0');
            power*=10;
            continue;
          }
 else {
            break;
          }
        }
      }
 else {
        matchStat=NOT_MATCH;
        return 0;
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
        return 0;
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
    if ((!exp) && count < 17) {
      value=(float)(((double)intVal) / power);
      if (negative) {
        value=-value;
      }
    }
 else {
      String text=this.subString(start,count);
      value=Float.parseFloat(text);
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
  if (chLocal == seperator) {
    bp+=offset;
    this.ch=this.charAt(bp);
    matchStat=VALUE;
    token=JSONToken.COMMA;
    return value;
  }
 else {
    matchStat=NOT_MATCH;
    return value;
  }
}
