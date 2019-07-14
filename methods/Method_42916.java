private static List<LimitOrder> createOrders(CurrencyPair currencyPair,OrderType orderType,List<BankeraOrderBook.OrderBookOrder> ordersList){
  List<LimitOrder> limitOrders=new ArrayList<>();
  if (ordersList == null)   return limitOrders;
  ordersList.forEach(order -> {
    limitOrders.add(new LimitOrder(orderType,new BigDecimal(order.getAmount()),currencyPair,String.valueOf(order.getId()),null,new BigDecimal(order.getPrice())));
  }
);
  return limitOrders;
}
