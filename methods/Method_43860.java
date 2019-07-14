private static List<LimitOrder> adaptMarketOrderToLimitOrder(CoinoneOrderBookData[] coinoneOrders,OrderType orderType,CurrencyPair currencyPair){
  List<LimitOrder> orders=new ArrayList<>(coinoneOrders.length);
  for (int i=0; i < coinoneOrders.length; i++) {
    CoinoneOrderBookData coinoneOrder=coinoneOrders[i];
    BigDecimal price=coinoneOrder.getPrice();
    BigDecimal amount=coinoneOrder.getQty();
    LimitOrder limitOrder=new LimitOrder(orderType,amount,currencyPair,null,null,price);
    orders.add(limitOrder);
  }
  return orders;
}
