private static CurrencyPair adaptSymbol(String symbol){
  try {
    return new CurrencyPair(symbol.substring(0,3),symbol.substring(3));
  }
 catch (  RuntimeException e) {
    throw new IllegalArgumentException("Not supported Coinbene symbol: " + symbol,e);
  }
}
