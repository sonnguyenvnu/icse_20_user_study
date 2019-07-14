public static OrderType convertType(String side){
  return "SELL".equals(side) ? OrderType.ASK : OrderType.BID;
}
