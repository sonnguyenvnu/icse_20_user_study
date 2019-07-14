public static OrderBook convertOrderBook(BinanceOrderbook ob,CurrencyPair pair){
  List<LimitOrder> bids=ob.bids.entrySet().stream().map(e -> new LimitOrder(OrderType.BID,e.getValue(),pair,null,null,e.getKey())).collect(Collectors.toList());
  List<LimitOrder> asks=ob.asks.entrySet().stream().map(e -> new LimitOrder(OrderType.ASK,e.getValue(),pair,null,null,e.getKey())).collect(Collectors.toList());
  return new OrderBook(null,asks,bids);
}
