public static List<LimitOrder> createOrders(List<CoinmateOrderBookEntry> coinmateOrders,Order.OrderType type,CurrencyPair currencyPair){
  List<LimitOrder> orders=new ArrayList<>(coinmateOrders.size());
  for (  CoinmateOrderBookEntry entry : coinmateOrders) {
    LimitOrder order=new LimitOrder(type,entry.getAmount(),currencyPair,null,null,entry.getPrice());
    orders.add(order);
  }
  return orders;
}
