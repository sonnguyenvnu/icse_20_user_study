public static Order.OrderType convert(CoindirectOrder.Side side){
switch (side) {
case BUY:
    return Order.OrderType.BID;
case SELL:
  return Order.OrderType.ASK;
default :
throw new RuntimeException("Not supported order side: " + side);
}
}
