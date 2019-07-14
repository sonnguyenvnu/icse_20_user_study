public static UserTrades adaptUserTrades(List<GateioTrade> userTrades){
  List<UserTrade> trades=new ArrayList<>();
  for (  GateioTrade userTrade : userTrades) {
    trades.add(adaptUserTrade(userTrade));
  }
  return new UserTrades(trades,TradeSortType.SortByTimestamp);
}
