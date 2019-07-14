public static Order.OrderType fromBl3pOrderType(String bl3pType){
  return bl3pType.equals("bid") ? Order.OrderType.BID : Order.OrderType.ASK;
}
