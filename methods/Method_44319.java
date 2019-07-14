public static HitbtcSide getSide(OrderType type){
  return type == OrderType.BID ? HitbtcSide.BUY : HitbtcSide.SELL;
}
