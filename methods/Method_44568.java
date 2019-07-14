public LivecoinOrderBook getOrderBookRaw(CurrencyPair currencyPair,int depth,Boolean groupByPrice) throws IOException {
  return service.getOrderBook(currencyPair.base.getCurrencyCode().toUpperCase(),currencyPair.counter.getCurrencyCode().toUpperCase(),depth,groupByPrice.toString());
}
