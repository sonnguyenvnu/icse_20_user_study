public static UserTrades adaptTradesFutures(OkCoinFuturesOrderResult orderResult){
  List<UserTrade> trades=new ArrayList<>(orderResult.getOrders().length);
  for (int i=0; i < orderResult.getOrders().length; i++) {
    OkCoinFuturesOrder order=orderResult.getOrders()[i];
    if (order.getDealAmount().equals(BigDecimal.ZERO)) {
      continue;
    }
    trades.add(adaptTradeFutures(order));
  }
  return new UserTrades(trades,TradeSortType.SortByTimestamp);
}
