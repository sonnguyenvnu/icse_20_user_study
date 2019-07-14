private static OrderType adaptSide(String side){
  return "sell".equals(side) ? ASK : BID;
}
