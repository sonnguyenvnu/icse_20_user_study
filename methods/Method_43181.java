private static List<LimitOrder> createOrder(CurrencyPair currencyPair,List<BithumbOrderbookEntry> orders,Order.OrderType orderType){
  return orders.stream().map(order -> createOrder(currencyPair,orderType,order.getQuantity(),order.getPrice())).collect(Collectors.toList());
}
