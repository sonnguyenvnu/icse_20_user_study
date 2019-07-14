public boolean scanFieldBoolean(char[] fieldName){
  matchStat=UNKNOWN;
  if (!charArrayCompare(fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return false;
  }
  int offset=fieldName.length;
  char chLocal=charAt(bp + (offset++));
  boolean value;
  if (chLocal == 't') {
    if (charAt(bp + (offset++)) != 'r') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (charAt(bp + (offset++)) != 'u') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (charAt(bp + (offset++)) != 'e') {
      matchStat=NOT_MATCH;
      return false;
    }
    value=true;
  }
 else   if (chLocal == 'f') {
    if (charAt(bp + (offset++)) != 'a') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (charAt(bp + (offset++)) != 'l') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (charAt(bp + (offset++)) != 's') {
      matchStat=NOT_MATCH;
      return false;
    }
    if (charAt(bp + (offset++)) != 'e') {
      matchStat=NOT_MATCH;
      return false;
    }
    value=false;
  }
 else {
    matchStat=NOT_MATCH;
    return false;
  }
  chLocal=charAt(bp + offset++);
  if (chLocal == ',') {
    bp+=offset;
    this.ch=this.charAt(bp);
    matchStat=VALUE;
    token=JSONToken.COMMA;
    return value;
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
      return false;
    }
    matchStat=END;
  }
 else {
    matchStat=NOT_MATCH;
    return false;
  }
  return value;
}
