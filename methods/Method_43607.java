private static CurrencyPair adaptSymbol(String symbol){
  try {
    String[] split=symbol.split("-");
    return new CurrencyPair(split[0],split[1]);
  }
 catch (  RuntimeException e) {
    throw new IllegalArgumentException("Not supported Cobinhood symbol: " + symbol);
  }
}
