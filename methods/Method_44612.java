private static Order.OrderType getOrderTypeFromVolumeSign(double volume){
  if (volume < 0) {
    return Order.OrderType.ASK;
  }
  return Order.OrderType.BID;
}
