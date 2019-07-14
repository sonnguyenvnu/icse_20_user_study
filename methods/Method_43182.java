private static LimitOrder createOrder(CurrencyPair currencyPair,Order.OrderType orderType,BigDecimal originalAmount,BigDecimal limitPric){
  return new LimitOrder(orderType,originalAmount,currencyPair,"",null,limitPric);
}
