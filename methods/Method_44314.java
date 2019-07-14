static CurrencyPair guessSymbol(String symbol){
  int splitIndex=symbol.endsWith("USDT") ? symbol.lastIndexOf("USDT") : symbol.length() - 3;
  return new CurrencyPair(symbol.substring(0,splitIndex),symbol.substring(splitIndex));
}
