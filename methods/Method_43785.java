private static List<LimitOrder> createOrders(CurrencyPair pair,Order.OrderType orderType,List<List<BigDecimal>> orders){
  List<LimitOrder> limitOrders=new ArrayList<>();
  if (orders == null)   return limitOrders;
  for (  List<BigDecimal> priceAndAmount : orders) {
    if (priceAndAmount.size() != 2) {
      throw new IllegalArgumentException("Expected a price and amount pair but received: " + priceAndAmount);
    }
    LimitOrder order=new LimitOrder(orderType,priceAndAmount.get(1),pair,"",null,priceAndAmount.get(0));
    limitOrders.add(order);
  }
  return limitOrders;
}
