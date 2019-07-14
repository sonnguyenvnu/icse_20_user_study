public static UserTrades adaptTradeHistory(HuobiOrder[] openOrders){
  OpenOrders orders=adaptOpenOrders(openOrders);
  List<UserTrade> trades=new ArrayList<>();
  for (  LimitOrder order : orders.getOpenOrders()) {
    trades.add(adaptTrade(order));
  }
  return new UserTrades(trades,TradeSortType.SortByTimestamp);
}
