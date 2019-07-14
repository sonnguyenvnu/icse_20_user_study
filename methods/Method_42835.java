public static AbucoinsOrder.Side adaptAbucoinsSide(OrderType orderType){
switch (orderType) {
case BID:
    return AbucoinsOrder.Side.buy;
case ASK:
  return AbucoinsOrder.Side.sell;
default :
logger.warn("Unrecognized OrderType " + orderType + " returning null for Side");
return null;
}
}
