private List<LimitOrder> toXChangeLimitOrders(Bl3pOrderBook.Bl3pOrderBookOrder[] bl3pOrders,Order.OrderType type,CurrencyPair currencyPair,Date timestamp){
  List<LimitOrder> orders=new ArrayList<>(bl3pOrders.length);
  for (  Bl3pOrderBook.Bl3pOrderBookOrder bl3pOrder : bl3pOrders) {
    LimitOrder order=new LimitOrder(type,new BigDecimal(bl3pOrder.getAmountInt()).multiply(new BigDecimal(1e8)),currencyPair,"",timestamp,new BigDecimal(bl3pOrder.getPriceInt()).multiply(new BigDecimal(1e5)));
    orders.add(order);
  }
  return orders;
}
