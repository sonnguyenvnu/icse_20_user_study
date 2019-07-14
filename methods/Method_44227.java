private static Stream<LimitOrder> adaptLimitOrders(Order.OrderType type,BigDecimal[][] list,Date timestamp,CurrencyPair currencyPair){
  return Arrays.stream(list).map(data -> adaptLimitOrder(type,data,currencyPair,null,timestamp));
}
