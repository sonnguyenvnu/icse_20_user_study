private static Map<OrderType,List<LimitOrder>> adaptMarketOrderToLimitOrder(UpbitOrderBookData[] upbitOrders,CurrencyPair currencyPair){
  List<LimitOrder> asks=new ArrayList<>(upbitOrders.length);
  List<LimitOrder> bids=new ArrayList<>(upbitOrders.length);
  Arrays.stream(upbitOrders).forEach(upbitOrder -> {
    OrderType orderType=OrderType.ASK;
    BigDecimal price=upbitOrder.getAskPrice();
    BigDecimal amount=upbitOrder.getAskSize();
    LimitOrder limitOrder=new LimitOrder(orderType,amount,currencyPair,null,null,price);
    asks.add(limitOrder);
    orderType=OrderType.BID;
    price=upbitOrder.getBidPrice();
    amount=upbitOrder.getBidSize();
    limitOrder=new LimitOrder(orderType,amount,currencyPair,null,null,price);
    bids.add(limitOrder);
  }
);
  Map<OrderType,List<LimitOrder>> map=new HashMap<>();
  map.put(OrderType.ASK,asks);
  map.put(OrderType.BID,bids);
  return map;
}
