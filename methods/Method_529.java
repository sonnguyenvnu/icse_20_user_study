public boolean scanBoolean(char expectNext){
  matchStat=UNKNOWN;
  int offset=0;
  char chLocal=charAt(bp + (offset++));
  boolean value=false;
  if (chLocal == 't') {
    if (charAt(bp + offset) == 'r' && charAt(bp + offset + 1) == 'u' && charAt(bp + offset + 2) == 'e') {
      offset+=3;
      chLocal=charAt(bp + (offset++));
      value=true;
    }
 else {
      matchStat=NOT_MATCH;
      return false;
    }
  }
 else   if (chLocal == 'f') {
    if (charAt(bp + offset) == 'a' && charAt(bp + offset + 1) == 'l' && charAt(bp + offset + 2) == 's' && charAt(bp + offset + 3) == 'e') {
      offset+=4;
      chLocal=charAt(bp + (offset++));
      value=false;
    }
 else {
      matchStat=NOT_MATCH;
      return false;
    }
  }
 else   if (chLocal == '1') {
    chLocal=charAt(bp + (offset++));
    value=true;
  }
 else   if (chLocal == '0') {
    chLocal=charAt(bp + (offset++));
    value=false;
  }
  for (; ; ) {
    if (chLocal == expectNext) {
      bp+=offset;
      this.ch=this.charAt(bp);
      matchStat=VALUE;
      return value;
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
