@Override public OrderBook getOrderBook(CurrencyPair currencyPair,Object... args) throws ExchangeException, IOException {
  try {
    PoloniexDepth depth=null;
    int depthLimit=999999;
    if (args != null && args.length > 0) {
      if (args[0] instanceof Integer) {
        depthLimit=(Integer)args[0];
      }
 else {
        throw new ExchangeException("Orderbook size argument must be an Integer!");
      }
    }
    depth=getPoloniexDepth(currencyPair,depthLimit);
    if (depth == null) {
      depth=getPoloniexDepth(currencyPair);
    }
    return PoloniexAdapters.adaptPoloniexDepth(depth,currencyPair);
  }
 catch (  PoloniexException e) {
    throw PoloniexErrorAdapter.adapt(e);
  }
}
