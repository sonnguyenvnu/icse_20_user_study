public static UserTrades adaptTrades(final Collection<IRippleTradeTransaction> tradesForAccount,final TradeHistoryParams params,final RippleAccountService accountService,final int roundingScale) throws IOException {
  final List<UserTrade> trades=new ArrayList<>();
  for (  final IRippleTradeTransaction orderDetails : tradesForAccount) {
    final UserTrade trade=adaptTrade(orderDetails,params,accountService,roundingScale);
    if (trade == null) {
    }
 else {
      trades.add(trade);
    }
  }
  return new UserTrades(trades,TradeSortType.SortByTimestamp);
}
