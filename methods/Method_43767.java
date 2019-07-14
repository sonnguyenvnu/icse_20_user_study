public static OrderBook adaptOrders(CoinEggOrders coinEggOrders,CurrencyPair currencyPair){
  List<LimitOrder> asks=Stream.of(coinEggOrders.getAsks()).map(order -> adaptOrder(order,OrderType.ASK,currencyPair)).collect(Collectors.toList());
  List<LimitOrder> bids=Stream.of(coinEggOrders.getBids()).map(order -> adaptOrder(order,OrderType.BID,currencyPair)).collect(Collectors.toList());
  return new OrderBook(null,asks,bids);
}
