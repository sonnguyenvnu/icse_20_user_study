public String addSymbol(String buffer,int offset,int len,int hash,boolean replace){
  final int bucket=hash & indexMask;
  String symbol=symbols[bucket];
  if (symbol != null) {
    if (hash == symbol.hashCode() && len == symbol.length() && buffer.startsWith(symbol,offset)) {
      return symbol;
    }
    String str=subString(buffer,offset,len);
    if (replace) {
      symbols[bucket]=str;
    }
    return str;
  }
  symbol=len == buffer.length() ? buffer : subString(buffer,offset,len);
  symbol=symbol.intern();
  symbols[bucket]=symbol;
  return symbol;
}
