public static OrdersContainer adaptOrders(List<BitmexPublicOrder> orders,CurrencyPair currencyPair,OrderType orderType,boolean reverse){
  long maxTimestamp=System.currentTimeMillis();
  LimitOrder[] limitOrders=new LimitOrder[orders.size()];
  int i=reverse ? orders.size() - 1 : 0;
  for (  BitmexPublicOrder order : orders) {
    limitOrders[i]=adaptOrder(order,orderType,currencyPair);
    i+=(reverse ? -1 : 1);
  }
  return new OrdersContainer(maxTimestamp,Arrays.asList(limitOrders));
}
