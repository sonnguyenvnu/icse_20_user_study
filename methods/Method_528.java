public final int[] scanFieldIntArray(char[] fieldName){
  matchStat=UNKNOWN;
  if (!charArrayCompare(fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return null;
  }
  int offset=fieldName.length;
  char chLocal=charAt(bp + (offset++));
  if (chLocal != '[') {
    matchStat=NOT_MATCH_NAME;
    return null;
  }
  chLocal=charAt(bp + (offset++));
  int[] array=new int[16];
  int arrayIndex=0;
  if (chLocal == ']') {
    chLocal=charAt(bp + (offset++));
  }
 else {
    for (; ; ) {
      boolean nagative=false;
      if (chLocal == '-') {
        chLocal=charAt(bp + (offset++));
        nagative=true;
      }
      if (chLocal >= '0' && chLocal <= '9') {
        int value=chLocal - '0';
        for (; ; ) {
          chLocal=charAt(bp + (offset++));
          if (chLocal >= '0' && chLocal <= '9') {
            value=value * 10 + (chLocal - '0');
          }
 else {
            break;
          }
        }
        if (arrayIndex >= array.length) {
          int[] tmp=new int[array.length * 3 / 2];
          System.arraycopy(array,0,tmp,0,arrayIndex);
          array=tmp;
        }
        array[arrayIndex++]=nagative ? -value : value;
        if (chLocal == ',') {
          chLocal=charAt(bp + (offset++));
        }
 else         if (chLocal == ']') {
          chLocal=charAt(bp + (offset++));
          break;
        }
      }
 else {
        matchStat=NOT_MATCH;
        return null;
      }
    }
  }
  if (arrayIndex != array.length) {
    int[] tmp=new int[arrayIndex];
    System.arraycopy(array,0,tmp,0,arrayIndex);
    array=tmp;
  }
  if (chLocal == ',') {
    bp+=(offset - 1);
    this.next();
    matchStat=VALUE;
    token=JSONToken.COMMA;
    return array;
  }
  if (chLocal == '}') {
    chLocal=charAt(bp + (offset++));
    if (chLocal == ',') {
      token=JSONToken.COMMA;
      bp+=(offset - 1);
      this.next();
    }
 else     if (chLocal == ']') {
      token=JSONToken.RBRACKET;
      bp+=(offset - 1);
      this.next();
    }
 else     if (chLocal == '}') {
      token=JSONToken.RBRACE;
      bp+=(offset - 1);
      this.next();
    }
 else     if (chLocal == EOI) {
      bp+=(offset - 1);
      token=JSONToken.EOF;
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
  return array;
}
