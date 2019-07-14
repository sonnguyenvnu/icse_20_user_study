public static Order.OrderType bitMarketOrderTypeToOrderType(String bitmarketOrderType){
  return bitmarketOrderType.equals("sell") ? Order.OrderType.ASK : Order.OrderType.BID;
}
