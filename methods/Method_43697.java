public static CoinbaseProPlaceOrder.Stop adaptStop(OrderType orderType){
  return orderType == OrderType.ASK ? CoinbaseProPlaceOrder.Stop.loss : CoinbaseProPlaceOrder.Stop.entry;
}
