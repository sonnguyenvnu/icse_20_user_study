private OrderType mapTradeType(String side){
  if ("sell".equals(side)) {
    return OrderType.ASK;
  }
 else   if ("buy".equals(side)) {
    return OrderType.BID;
  }
  return null;
}
