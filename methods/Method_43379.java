public static OrderBook adaptOrders(BitZOrders bitZOrders,CurrencyPair currencyPair){
  Date timestamp=DateUtils.fromMillisUtc(bitZOrders.getTimestamp());
  List<LimitOrder> asks=new ArrayList<LimitOrder>();
  List<LimitOrder> bids=new ArrayList<LimitOrder>();
  for (  BitZPublicOrder order : bitZOrders.getAsks()) {
    asks.add(new LimitOrder.Builder(OrderType.ASK,currencyPair).averagePrice(order.getPrice()).originalAmount(order.getVolume()).build());
  }
  for (  BitZPublicOrder order : bitZOrders.getBids()) {
    bids.add(new LimitOrder.Builder(OrderType.BID,currencyPair).averagePrice(order.getPrice()).originalAmount(order.getVolume()).build());
  }
  return new OrderBook(timestamp,asks,bids);
}
