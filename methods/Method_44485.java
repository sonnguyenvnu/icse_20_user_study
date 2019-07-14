private static LimitOrder adaptLimitOrder(CurrencyPair currencyPair,OrderType orderType,PriceAndSize priceAndSize,Date timestamp){
  return new LimitOrder.Builder(orderType,currencyPair).limitPrice(priceAndSize.price).originalAmount(priceAndSize.size).orderStatus(NEW).build();
}
