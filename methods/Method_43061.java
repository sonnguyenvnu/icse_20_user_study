public static List<LimitOrder> createOrders(CurrencyPair currencyPair,Order.OrderType orderType,CondensedOrder[] condensedOrders){
  List<LimitOrder> limitOrders=new ArrayList<>();
  for (int i=0; i < condensedOrders.length; i++) {
    LimitOrder limitOrder=new LimitOrder(orderType,condensedOrders[i].getVolume(),currencyPair,"",null,condensedOrders[i].getPrice());
    limitOrders.add(limitOrder);
  }
  return limitOrders;
}
