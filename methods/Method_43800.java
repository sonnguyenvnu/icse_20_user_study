public static LimitOrder createOrder(CurrencyPair currencyPair,List<BigDecimal> priceAndAmount,OrderType orderType){
  return new LimitOrder(orderType,priceAndAmount.get(1),currencyPair,"",null,priceAndAmount.get(0));
}
