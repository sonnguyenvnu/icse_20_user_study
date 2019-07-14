public static OrderSide convert(OrderType type){
switch (type) {
case ASK:
    return OrderSide.SELL;
case BID:
  return OrderSide.BUY;
default :
throw new RuntimeException("Not supported order type: " + type);
}
}
