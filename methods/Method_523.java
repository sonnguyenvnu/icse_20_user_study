public String scanSymbolWithSeperator(final SymbolTable symbolTable,char serperator){
  matchStat=UNKNOWN;
  int offset=0;
  char chLocal=charAt(bp + (offset++));
  if (chLocal == 'n') {
    if (charAt(bp + offset) == 'u' && charAt(bp + offset + 1) == 'l' && charAt(bp + offset + 2) == 'l') {
      offset+=3;
      chLocal=charAt(bp + (offset++));
    }
 else {
      matchStat=NOT_MATCH;
      return null;
    }
    if (chLocal == serperator) {
      bp+=offset;
      this.ch=this.charAt(bp);
      matchStat=VALUE;
      return null;
    }
 else {
      matchStat=NOT_MATCH;
      return null;
    }
  }
  if (chLocal != '"') {
    matchStat=NOT_MATCH;
    return null;
  }
  String strVal;
  int hash=0;
  for (; ; ) {
    chLocal=charAt(bp + (offset++));
    if (chLocal == '\"') {
      int start=bp + 0 + 1;
      int len=bp + offset - start - 1;
      strVal=addSymbol(start,len,hash,symbolTable);
      chLocal=charAt(bp + (offset++));
      break;
    }
    hash=31 * hash + chLocal;
    if (chLocal == '\\') {
      matchStat=NOT_MATCH;
      return null;
    }
  }
  for (; ; ) {
    if (chLocal == serperator) {
      bp+=offset;
      this.ch=this.charAt(bp);
      matchStat=VALUE;
      return strVal;
    }
 else {
      if (isWhitespace(chLocal)) {
        chLocal=charAt(bp + (offset++));
        continue;
      }
      matchStat=NOT_MATCH;
      return strVal;
    }
  }
}
