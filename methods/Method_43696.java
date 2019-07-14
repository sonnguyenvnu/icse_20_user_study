public static CoinbaseProPlaceOrder.Side adaptSide(OrderType orderType){
  return orderType == OrderType.ASK ? CoinbaseProPlaceOrder.Side.sell : CoinbaseProPlaceOrder.Side.buy;
}
