private void collectCurrenciesAndPairs(BitmexTicker ticker,List<CurrencyPair> activeCurrencyPairs,Set<Currency> activeCurrencies){
  String bitmexSymbol=ticker.getSymbol();
  String baseSymbol=ticker.getRootSymbol();
  String counterSymbol;
  if (bitmexSymbol.contains(baseSymbol)) {
    counterSymbol=bitmexSymbol.substring(baseSymbol.length(),bitmexSymbol.length());
  }
 else {
    throw new ExchangeException("Not clear how to create currency pair for symbol: " + bitmexSymbol);
  }
  activeCurrencyPairs.add(new CurrencyPair(baseSymbol,counterSymbol));
  activeCurrencies.add(new Currency(baseSymbol));
  activeCurrencies.add(new Currency(counterSymbol));
}
