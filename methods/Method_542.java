public java.util.UUID scanFieldUUID(char[] fieldName){
  matchStat=UNKNOWN;
  if (!charArrayCompare(fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return null;
  }
  int offset=fieldName.length;
  char chLocal=charAt(bp + (offset++));
  final java.util.UUID uuid;
  if (chLocal == '"') {
    int startIndex=bp + fieldName.length + 1;
    int endIndex=indexOf('"',startIndex);
    if (endIndex == -1) {
      throw new JSONException("unclosed str");
    }
    int startIndex2=bp + fieldName.length + 1;
    int len=endIndex - startIndex2;
    if (len == 36) {
      long mostSigBits=0, leastSigBits=0;
      for (int i=0; i < 8; ++i) {
        char ch=charAt(startIndex2 + i);
        int num;
        if (ch >= '0' && ch <= '9') {
          num=ch - '0';
        }
 else         if (ch >= 'a' && ch <= 'f') {
          num=10 + (ch - 'a');
        }
 else         if (ch >= 'A' && ch <= 'F') {
          num=10 + (ch - 'A');
        }
 else {
          matchStat=NOT_MATCH_NAME;
          return null;
        }
        mostSigBits<<=4;
        mostSigBits|=num;
      }
      for (int i=9; i < 13; ++i) {
        char ch=charAt(startIndex2 + i);
        int num;
        if (ch >= '0' && ch <= '9') {
          num=ch - '0';
        }
 else         if (ch >= 'a' && ch <= 'f') {
          num=10 + (ch - 'a');
        }
 else         if (ch >= 'A' && ch <= 'F') {
          num=10 + (ch - 'A');
        }
 else {
          matchStat=NOT_MATCH_NAME;
          return null;
        }
        mostSigBits<<=4;
        mostSigBits|=num;
      }
      for (int i=14; i < 18; ++i) {
        char ch=charAt(startIndex2 + i);
        int num;
        if (ch >= '0' && ch <= '9') {
          num=ch - '0';
        }
 else         if (ch >= 'a' && ch <= 'f') {
          num=10 + (ch - 'a');
        }
 else         if (ch >= 'A' && ch <= 'F') {
          num=10 + (ch - 'A');
        }
 else {
          matchStat=NOT_MATCH_NAME;
          return null;
        }
        mostSigBits<<=4;
        mostSigBits|=num;
      }
      for (int i=19; i < 23; ++i) {
        char ch=charAt(startIndex2 + i);
        int num;
        if (ch >= '0' && ch <= '9') {
          num=ch - '0';
        }
 else         if (ch >= 'a' && ch <= 'f') {
          num=10 + (ch - 'a');
        }
 else         if (ch >= 'A' && ch <= 'F') {
          num=10 + (ch - 'A');
        }
 else {
          matchStat=NOT_MATCH_NAME;
          return null;
        }
        leastSigBits<<=4;
        leastSigBits|=num;
      }
      for (int i=24; i < 36; ++i) {
        char ch=charAt(startIndex2 + i);
        int num;
        if (ch >= '0' && ch <= '9') {
          num=ch - '0';
        }
 else         if (ch >= 'a' && ch <= 'f') {
          num=10 + (ch - 'a');
        }
 else         if (ch >= 'A' && ch <= 'F') {
          num=10 + (ch - 'A');
        }
 else {
          matchStat=NOT_MATCH_NAME;
          return null;
        }
        leastSigBits<<=4;
        leastSigBits|=num;
      }
      uuid=new UUID(mostSigBits,leastSigBits);
      offset+=(endIndex - (bp + fieldName.length + 1) + 1);
      chLocal=charAt(bp + (offset++));
    }
 else     if (len == 32) {
      long mostSigBits=0, leastSigBits=0;
      for (int i=0; i < 16; ++i) {
        char ch=charAt(startIndex2 + i);
        int num;
        if (ch >= '0' && ch <= '9') {
          num=ch - '0';
        }
 else         if (ch >= 'a' && ch <= 'f') {
          num=10 + (ch - 'a');
        }
 else         if (ch >= 'A' && ch <= 'F') {
          num=10 + (ch - 'A');
        }
 else {
          matchStat=NOT_MATCH_NAME;
          return null;
        }
        mostSigBits<<=4;
        mostSigBits|=num;
      }
      for (int i=16; i < 32; ++i) {
        char ch=charAt(startIndex2 + i);
        int num;
        if (ch >= '0' && ch <= '9') {
          num=ch - '0';
        }
 else         if (ch >= 'a' && ch <= 'f') {
          num=10 + (ch - 'a');
        }
 else         if (ch >= 'A' && ch <= 'F') {
          num=10 + (ch - 'A');
        }
 else {
          matchStat=NOT_MATCH_NAME;
          return null;
        }
        leastSigBits<<=4;
        leastSigBits|=num;
      }
      uuid=new UUID(mostSigBits,leastSigBits);
      offset+=(endIndex - (bp + fieldName.length + 1) + 1);
      chLocal=charAt(bp + (offset++));
    }
 else {
      matchStat=NOT_MATCH;
      return null;
    }
  }
 else   if (chLocal == 'n' && charAt(bp + (offset++)) == 'u' && charAt(bp + (offset++)) == 'l' && charAt(bp + (offset++)) == 'l') {
    uuid=null;
    chLocal=charAt(bp + (offset++));
  }
 else {
    matchStat=NOT_MATCH;
    return null;
  }
  if (chLocal == ',') {
    bp+=offset;
    this.ch=this.charAt(bp);
    matchStat=VALUE;
    return uuid;
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
  return uuid;
}
