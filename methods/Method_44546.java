public static UserTrades adaptTradesHistory(final Map<Long,LiquiUserTrade> liquiTrades){
  final List<UserTrade> trades=new ArrayList<>();
  for (  final Map.Entry<Long,LiquiUserTrade> entry : liquiTrades.entrySet()) {
    trades.add(adaptTrade(entry.getValue(),entry.getKey()));
  }
  return new UserTrades(trades,Trades.TradeSortType.SortByID);
}
