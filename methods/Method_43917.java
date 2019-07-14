public List<LimitOrder> getOrders(OrderType type){
  return type == OrderType.ASK ? asks : bids;
}
