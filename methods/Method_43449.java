public static UserTrades adaptTradeHistory(List<BTCMarketsUserTrade> btcmarketsUserTrades,CurrencyPair currencyPair){
  List<UserTrade> trades=new ArrayList<>();
  for (  BTCMarketsUserTrade btcmarketsUserTrade : btcmarketsUserTrades) {
    trades.add(adaptTrade(btcmarketsUserTrade,currencyPair));
  }
  return new UserTrades(trades,TradeSortType.SortByID);
}
