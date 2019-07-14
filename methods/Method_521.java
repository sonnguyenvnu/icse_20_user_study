public long scanFieldSymbol(char[] fieldName){
  matchStat=UNKNOWN;
  if (!charArrayCompare(fieldName)) {
    matchStat=NOT_MATCH_NAME;
    return 0;
  }
  int offset=fieldName.length;
  char chLocal=charAt(bp + (offset++));
  if (chLocal != '"') {
    matchStat=NOT_MATCH;
    return 0;
  }
  long hash=0xcbf29ce484222325L;
  for (; ; ) {
    chLocal=charAt(bp + (offset++));
    if (chLocal == '\"') {
      chLocal=charAt(bp + (offset++));
      break;
    }
    hash^=chLocal;
    hash*=0x100000001b3L;
    if (chLocal == '\\') {
      matchStat=NOT_MATCH;
      return 0;
    }
  }
  if (chLocal == ',') {
    bp+=offset;
    this.ch=this.charAt(bp);
    matchStat=VALUE;
    return hash;
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
      return 0;
    }
    matchStat=END;
  }
 else {
    matchStat=NOT_MATCH;
    return 0;
  }
  return hash;
}
