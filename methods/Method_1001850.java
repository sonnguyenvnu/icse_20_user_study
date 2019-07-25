private static USymbol guess(USymbol symbol,PackageStyle style){
  if (symbol != null) {
    return symbol;
  }
  return style.toUSymbol();
}
