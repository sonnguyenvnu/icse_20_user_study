public static String adaptOrderType(Order.OrderType orderType){
  return (orderType.equals(Order.OrderType.ASK)) ? "sell" : "buy";
}
