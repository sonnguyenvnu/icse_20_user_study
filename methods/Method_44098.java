public static LimitOrder adaptOrders(BigDecimal amount,BigDecimal price,CurrencyPair currencyPair,OrderType orderType,String id){
  return new LimitOrder(orderType,amount,currencyPair,id,null,price);
}
