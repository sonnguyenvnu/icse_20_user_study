/** 
 * Adapt the user's trades 
 */
public static UserTrades adaptTradeHistory(CryptonitUserTransaction[] cryptonitUserTransactions){
  List<UserTrade> trades=new ArrayList<>();
  long lastTradeId=0;
  for (  CryptonitUserTransaction t : cryptonitUserTransactions) {
    if (!t.getType().equals(CryptonitUserTransaction.TransactionType.trade)) {
      continue;
    }
    final OrderType orderType;
    if (t.getCounterAmount().doubleValue() == 0.0) {
      orderType=t.getBaseAmount().doubleValue() < 0.0 ? OrderType.ASK : OrderType.BID;
    }
 else {
      orderType=t.getCounterAmount().doubleValue() > 0.0 ? OrderType.ASK : OrderType.BID;
    }
    long tradeId=t.getId();
    if (tradeId > lastTradeId) {
      lastTradeId=tradeId;
    }
    final CurrencyPair pair=new CurrencyPair(t.getBaseCurrency().toUpperCase(),t.getCounterCurrency().toUpperCase());
    UserTrade trade=new UserTrade(orderType,t.getBaseAmount().abs(),pair,t.getPrice().abs(),t.getDatetime(),Long.toString(tradeId),Long.toString(t.getOrderId()),t.getFee(),Currency.getInstance(t.getFeeCurrency().toUpperCase()));
    trades.add(trade);
  }
  return new UserTrades(trades,lastTradeId,TradeSortType.SortByID);
}
