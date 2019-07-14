public String scanFieldString(char[] fieldName){
  matchStat=UNKNOWN;
  int startPos=this.bp;
  char startChar=this.ch;
  for (; ; ) {
    if (!charArrayCompare(text,bp,fieldName)) {
      if (isWhitespace(ch)) {
        next();
        continue;
      }
      matchStat=NOT_MATCH_NAME;
      return stringDefaultValue();
    }
 else {
      break;
    }
  }
  int index=bp + fieldName.length;
  char ch=charAt(index++);
  if (ch != '"') {
    matchStat=NOT_MATCH;
    return stringDefaultValue();
  }
  final String strVal;
{
    int startIndex=index;
    int endIndex=indexOf('"',startIndex);
    if (endIndex == -1) {
      throw new JSONException("unclosed str");
    }
    String stringVal=subString(startIndex,endIndex - startIndex);
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
      int chars_len=endIndex - (bp + fieldName.length + 1);
      char[] chars=sub_chars(bp + fieldName.length + 1,chars_len);
      stringVal=readString(chars,chars_len);
    }
    ch=charAt(endIndex + 1);
    for (; ; ) {
      if (ch == ',' || ch == '}') {
        bp=endIndex + 1;
        this.ch=ch;
        strVal=stringVal;
        break;
      }
 else       if (isWhitespace(ch)) {
        endIndex++;
        ch=charAt(endIndex + 1);
      }
 else {
        matchStat=NOT_MATCH;
        return stringDefaultValue();
      }
    }
  }
  if (ch == ',') {
    this.ch=charAt(++bp);
    matchStat=VALUE;
    return strVal;
  }
 else {
    ch=charAt(++bp);
    if (ch == ',') {
      token=JSONToken.COMMA;
      this.ch=charAt(++bp);
    }
 else     if (ch == ']') {
      token=JSONToken.RBRACKET;
      this.ch=charAt(++bp);
    }
 else     if (ch == '}') {
      token=JSONToken.RBRACE;
      this.ch=charAt(++bp);
    }
 else     if (ch == EOI) {
      token=JSONToken.EOF;
    }
 else {
      this.bp=startPos;
      this.ch=startChar;
      matchStat=NOT_MATCH;
      return stringDefaultValue();
    }
    matchStat=END;
  }
  return strVal;
}
