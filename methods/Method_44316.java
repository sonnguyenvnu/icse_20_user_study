private static List<LimitOrder> adaptMarketOrderToLimitOrder(HitbtcOrderLimit[] hitbtcOrders,OrderType orderType,CurrencyPair currencyPair){
  List<LimitOrder> orders=new ArrayList<>(hitbtcOrders.length);
  for (  HitbtcOrderLimit hitbtcOrderLimit : hitbtcOrders) {
    LimitOrder limitOrder=new LimitOrder(orderType,hitbtcOrderLimit.getSize(),currencyPair,null,null,hitbtcOrderLimit.getPrice());
    orders.add(limitOrder);
  }
  return orders;
}
