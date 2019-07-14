public UserTrades getOrderTrades(String orderId,CurrencyPair currencyPair) throws IOException {
  try {
    List<UserTrade> trades=new ArrayList<>();
    PoloniexUserTrade[] poloniexUserTrades=returnOrderTrades(orderId);
    if (poloniexUserTrades != null) {
      for (      PoloniexUserTrade poloniexUserTrade : poloniexUserTrades) {
        poloniexUserTrade.setOrderNumber(orderId);
        trades.add(PoloniexAdapters.adaptPoloniexUserTrade(poloniexUserTrade,currencyPair));
      }
    }
    return new UserTrades(trades,TradeSortType.SortByTimestamp);
  }
 catch (  PoloniexException e) {
    throw PoloniexErrorAdapter.adapt(e);
  }
}
