private String composeOrderId(String id,Order.OrderType orderType){
  CampBX.OrderType type=orderType == Order.OrderType.ASK ? CampBX.OrderType.Sell : CampBX.OrderType.Buy;
  return composeOrderId(type,id);
}
