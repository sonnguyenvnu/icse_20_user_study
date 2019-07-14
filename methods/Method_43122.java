public List<CurrencyPair> getExchangeSymbols() throws IOException {
  List<CurrencyPair> currencyPairs=new ArrayList<>();
  for (  String symbol : bitfinex.getSymbols()) {
    currencyPairs.add(BitfinexAdapters.adaptCurrencyPair(symbol));
  }
  return currencyPairs;
}
