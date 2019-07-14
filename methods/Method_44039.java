private static LimitOrder createOrder(CurrencyPair currencyPair,CryptopiaOrder cryptopiaOrder,Order.OrderType orderType){
  return new LimitOrder(orderType,cryptopiaOrder.getVolume(),currencyPair,"",null,cryptopiaOrder.getPrice());
}
