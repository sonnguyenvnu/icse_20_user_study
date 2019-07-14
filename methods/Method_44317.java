public static OrderType adaptSide(HitbtcSide side){
switch (side) {
case BUY:
    return OrderType.BID;
case SELL:
  return OrderType.ASK;
default :
return null;
}
}
