@SuppressWarnings("unchecked") public Collection<String> scanFieldStringArray(char[] fieldName,Class<?> type){
  matchStat=UNKNOWN;
  while (ch == '\n' || ch == ' ') {
    int index=++bp;
    ch=(index >= this.len ? EOI : text.charAt(index));
  }
  if (!charArrayCompare(text,bp,fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return null;
  }
  Collection<String> list=newCollectionByType(type);
  int startPos=this.bp;
  char startChar=this.ch;
  int index=bp + fieldName.length;
  char ch=charAt(index++);
  if (ch == '[') {
    ch=charAt(index++);
    for (; ; ) {
      if (ch == '"') {
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
          int chars_len=endIndex - startIndex;
          char[] chars=sub_chars(startIndex,chars_len);
          stringVal=readString(chars,chars_len);
        }
        index=endIndex + 1;
        ch=charAt(index++);
        list.add(stringVal);
      }
 else       if (ch == 'n' && text.startsWith("ull",index)) {
        index+=3;
        ch=charAt(index++);
        list.add(null);
      }
 else       if (ch == ']' && list.size() == 0) {
        ch=charAt(index++);
        break;
      }
 else {
        matchStat=NOT_MATCH;
        return null;
      }
      if (ch == ',') {
        ch=charAt(index++);
        continue;
      }
      if (ch == ']') {
        ch=charAt(index++);
        while (isWhitespace(ch)) {
          ch=charAt(index++);
        }
        break;
      }
      matchStat=NOT_MATCH;
      return null;
    }
  }
 else   if (text.startsWith("ull",index)) {
    index+=3;
    ch=charAt(index++);
    list=null;
  }
 else {
    matchStat=NOT_MATCH;
    return null;
  }
  bp=index;
  if (ch == ',') {
    this.ch=charAt(bp);
    matchStat=VALUE;
    return list;
  }
 else   if (ch == '}') {
    ch=charAt(bp);
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
        this.ch=ch;
        break;
      }
 else {
        boolean space=false;
        while (isWhitespace(ch)) {
          ch=charAt(index++);
          bp=index;
          space=true;
        }
        if (space) {
          continue;
        }
        matchStat=NOT_MATCH;
        return null;
      }
    }
    matchStat=END;
  }
 else {
    this.ch=startChar;
    bp=startPos;
    matchStat=NOT_MATCH;
    return null;
  }
  return list;
}
