public List<OrderBook> getOrderBooks(Integer depth,Collection<CurrencyPair> currencyPairs){
  if (depth == null) {
    depth=200;
  }
  List<BiboxOrderBook> biboxOrderBooks=getBiboxOrderBooks(depth,currencyPairs);
  return BiboxAdapters.adaptAllOrderBooks(biboxOrderBooks);
}
