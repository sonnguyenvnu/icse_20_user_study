private static List<LimitOrder> transformArrayToLimitOrders(BigDecimal[][] orders,OrderType orderType,CurrencyPair currencyPair){
  List<LimitOrder> limitOrders=new ArrayList<>();
  for (  BigDecimal[] order : orders) {
    limitOrders.add(new LimitOrder(orderType,order[1],currencyPair,null,null,order[0]));
  }
  return limitOrders;
}
