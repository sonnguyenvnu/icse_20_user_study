public Map<CurrencyPair,OrderBook> getOrderBooks() throws IOException {
  Map<CurrencyPair,GateioDepth> gateioDepths=super.getGateioDepths();
  Map<CurrencyPair,OrderBook> orderBooks=new HashMap<>(gateioDepths.size());
  gateioDepths.forEach((currencyPair,gateioDepth) -> {
    OrderBook orderBook=GateioAdapters.adaptOrderBook(gateioDepth,currencyPair);
    orderBooks.put(currencyPair,orderBook);
  }
);
  return orderBooks;
}
