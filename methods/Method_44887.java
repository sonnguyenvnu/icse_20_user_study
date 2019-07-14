public static TheRockOrder.Side adaptSide(OrderType type){
  return type == BID ? TheRockOrder.Side.buy : TheRockOrder.Side.sell;
}
