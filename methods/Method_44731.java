public static OrderBook adaptPoloniexDepth(PoloniexDepth depth,CurrencyPair currencyPair){
  List<LimitOrder> asks=adaptPoloniexPublicOrders(depth.getAsks(),OrderType.ASK,currencyPair);
  List<LimitOrder> bids=adaptPoloniexPublicOrders(depth.getBids(),OrderType.BID,currencyPair);
  return new OrderBook(null,asks,bids);
}
