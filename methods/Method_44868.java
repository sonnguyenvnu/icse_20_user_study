@Override public OrderBook getOrderBook(CurrencyPair currencyPair,Object... args) throws IOException {
  exchange.maybeThrow();
  return exchange.getEngine(currencyPair).level2();
}
