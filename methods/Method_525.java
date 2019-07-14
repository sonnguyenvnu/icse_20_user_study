@SuppressWarnings("unchecked") public Collection<String> scanFieldStringArray(char[] fieldName,Class<?> type){
  matchStat=UNKNOWN;
  if (!charArrayCompare(fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return null;
  }
  Collection<String> list=newCollectionByType(type);
  int offset=fieldName.length;
  char chLocal=charAt(bp + (offset++));
  if (chLocal != '[') {
    matchStat=NOT_MATCH;
    return null;
  }
  chLocal=charAt(bp + (offset++));
  for (; ; ) {
    if (chLocal == '"') {
      int startIndex=bp + offset;
      int endIndex=indexOf('"',startIndex);
      if (endIndex == -1) {
        throw new JSONException("unclosed str");
      }
      int startIndex2=bp + offset;
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
        int chars_len=endIndex - (bp + offset);
        char[] chars=sub_chars(bp + offset,chars_len);
        stringVal=readString(chars,chars_len);
      }
      offset+=(endIndex - (bp + offset) + 1);
      chLocal=charAt(bp + (offset++));
      list.add(stringVal);
    }
 else     if (chLocal == 'n' && charAt(bp + offset) == 'u' && charAt(bp + offset + 1) == 'l' && charAt(bp + offset + 2) == 'l') {
      offset+=3;
      chLocal=charAt(bp + (offset++));
      list.add(null);
    }
 else     if (chLocal == ']' && list.size() == 0) {
      chLocal=charAt(bp + (offset++));
      break;
    }
 else {
      throw new JSONException("illega str");
    }
    if (chLocal == ',') {
      chLocal=charAt(bp + (offset++));
      continue;
    }
    if (chLocal == ']') {
      chLocal=charAt(bp + (offset++));
      break;
    }
    matchStat=NOT_MATCH;
    return null;
  }
  if (chLocal == ',') {
    bp+=offset;
    this.ch=this.charAt(bp);
    matchStat=VALUE;
    return list;
  }
  if (chLocal == '}') {
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
      bp+=(offset - 1);
      token=JSONToken.EOF;
      this.ch=EOI;
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
  return list;
}
