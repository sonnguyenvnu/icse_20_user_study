public static CoindirectOrder.Side convert(Order.OrderType type){
switch (type) {
case BID:
    return CoindirectOrder.Side.BUY;
case ASK:
  return CoindirectOrder.Side.SELL;
default :
throw new RuntimeException("Not supported order side: " + type);
}
}
