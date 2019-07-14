public static String orderTypeToBitMarketOrderType(Order.OrderType orderType){
  return orderType == Order.OrderType.ASK ? "sell" : "buy";
}
