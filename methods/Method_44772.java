public static UserTrades adaptTradeHistory(QuadrigaCxUserTransaction[] quadrigacxUserTransactions,CurrencyPair currencyPair){
  List<UserTrade> trades=new ArrayList<>();
  for (  QuadrigaCxUserTransaction quadrigacxUserTransaction : quadrigacxUserTransactions) {
    if (quadrigacxUserTransaction.getType().equals(QuadrigaCxUserTransaction.TransactionType.trade)) {
      UserTrade trade=adaptTrade(currencyPair,quadrigacxUserTransaction);
      trades.add(trade);
    }
  }
  return new UserTrades(trades,TradeSortType.SortByTimestamp);
}
