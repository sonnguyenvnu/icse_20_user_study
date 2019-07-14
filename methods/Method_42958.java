public static OrderType convert(OrderSide side){
switch (side) {
case BUY:
    return OrderType.BID;
case SELL:
  return OrderType.ASK;
default :
throw new RuntimeException("Not supported order side: " + side);
}
}
