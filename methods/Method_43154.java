private static Order.OrderType adaptSide(String side){
  return "BUY".equals(side) ? Order.OrderType.ASK : Order.OrderType.BID;
}
