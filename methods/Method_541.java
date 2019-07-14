public java.util.Date scanDate(char seperator){
  matchStat=UNKNOWN;
  int offset=0;
  char chLocal=charAt(bp + (offset++));
  final java.util.Date dateVal;
  if (chLocal == '"') {
    int startIndex=bp + 1;
    int endIndex=indexOf('"',startIndex);
    if (endIndex == -1) {
      throw new JSONException("unclosed str");
    }
    int startIndex2=bp + 1;
    String stringVal=subString(startIndex2,endIndex - startIndex2);
    if (stringVal.indexOf('\\') != -1) {
      for (; ; ) {
        int slashCount=0;
        for (int i=endIndex - 1; i >= 0; --i) {
          if (charAt(i) == '\\') {
            slashCount++;
          }
 else {
            break;
          }
        }
        if (slashCount % 2 == 0) {
          break;
        }
        endIndex=indexOf('"',endIndex + 1);
      }
      int chars_len=endIndex - (bp + 1);
      char[] chars=sub_chars(bp + 1,chars_len);
      stringVal=readString(chars,chars_len);
    }
    offset+=(endIndex - (bp + 1) + 1);
    chLocal=charAt(bp + (offset++));
    JSONScanner dateLexer=new JSONScanner(stringVal);
    try {
      if (dateLexer.scanISO8601DateIfMatch(false)) {
        Calendar calendar=dateLexer.getCalendar();
        dateVal=calendar.getTime();
      }
 else {
        matchStat=NOT_MATCH;
        return null;
      }
    }
  finally {
      dateLexer.close();
    }
  }
 else   if (chLocal == '-' || (chLocal >= '0' && chLocal <= '9')) {
    long millis=0;
    boolean negative=false;
    if (chLocal == '-') {
      chLocal=charAt(bp + (offset++));
      negative=true;
    }
    if (chLocal >= '0' && chLocal <= '9') {
      millis=chLocal - '0';
      for (; ; ) {
        chLocal=charAt(bp + (offset++));
        if (chLocal >= '0' && chLocal <= '9') {
          millis=millis * 10 + (chLocal - '0');
        }
 else {
          break;
        }
      }
    }
    if (millis < 0) {
      matchStat=NOT_MATCH;
      return null;
    }
    if (negative) {
      millis=-millis;
    }
    dateVal=new java.util.Date(millis);
  }
 else   if (chLocal == 'n' && charAt(bp + offset) == 'u' && charAt(bp + offset + 1) == 'l' && charAt(bp + offset + 2) == 'l') {
    matchStat=VALUE_NULL;
    dateVal=null;
    offset+=3;
    chLocal=charAt(bp + offset++);
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
    return dateVal;
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
  return dateVal;
}
