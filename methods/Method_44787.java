public static LimitOrder createOrder(CurrencyPair currencyPair,BigDecimal[] priceAndAmount,OrderType orderType){
  return new LimitOrder(orderType,priceAndAmount[1],currencyPair,"",null,priceAndAmount[0]);
}
