public static List<LimitOrder> createOrders(Order.OrderType orderType,List<BigDecimal[]> orders,CurrencyPair currencyPair){
  List<LimitOrder> limitOrders=new ArrayList<>();
  for (  BigDecimal[] o : orders) {
    limitOrders.add(new LimitOrder(orderType,o[1],currencyPair,null,null,o[0]));
  }
  return limitOrders;
}
