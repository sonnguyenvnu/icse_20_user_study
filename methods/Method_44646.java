public static UserTrades adaptTrades(OkCoinOrderResult orderResult){
  List<UserTrade> trades=new ArrayList<>(orderResult.getOrders().length);
  for (int i=0; i < orderResult.getOrders().length; i++) {
    OkCoinOrder order=orderResult.getOrders()[i];
    if (order.getDealAmount().equals(BigDecimal.ZERO)) {
      continue;
    }
    trades.add(adaptTrade(order));
  }
  return new UserTrades(trades,TradeSortType.SortByTimestamp);
}
