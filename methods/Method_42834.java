public static LimitOrder createOrder(CurrencyPair currencyPair,AbucoinsOrderBook.LimitOrder priceAndAmount,OrderType orderType){
  return new LimitOrder.Builder(orderType,currencyPair).averagePrice(priceAndAmount.getPrice()).limitPrice(priceAndAmount.getPrice()).orderStatus(OrderStatus.NEW).originalAmount(priceAndAmount.getSize()).build();
}
