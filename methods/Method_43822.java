@Override public List<CurrencyPair> getExchangeSymbols(){
  List<Currency> currencies=marketDataService.getCurrencies();
  List<CurrencyPair> pairs=new ArrayList<>();
  for (  Currency currency : currencies) {
    pairs.add(new CurrencyPair(currency,Currency.USD));
    pairs.add(new CurrencyPair(currency,Currency.BTC));
  }
  return pairs;
}
