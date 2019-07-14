public static CurrencyPair adaptCurrencyPair(String symbol){
  String[] split=symbol.split("-");
  if (split.length != 2) {
    throw new ExchangeException("Invalid kucoin symbol: " + symbol);
  }
  return new CurrencyPair(split[0],split[1]);
}
