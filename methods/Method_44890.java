public static UserTrades adaptUserTrades(TheRockUserTrades trades,CurrencyPair currencyPair) throws com.fasterxml.jackson.databind.exc.InvalidFormatException {
  List<UserTrade> tradesList=new ArrayList<>(trades.getCount());
  long lastTradeId=0;
  for (int i=0; i < trades.getCount(); i++) {
    TheRockUserTrade trade=trades.getTrades()[i];
    long tradeId=trade.getId();
    if (tradeId > lastTradeId)     lastTradeId=tradeId;
    tradesList.add(adaptUserTrade(trade,currencyPair));
  }
  return new UserTrades(tradesList,lastTradeId,Trades.TradeSortType.SortByID);
}
