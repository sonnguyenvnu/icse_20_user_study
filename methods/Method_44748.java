private UserTrades getTradeHistory(CurrencyPair currencyPair,final Long startTime,final Long endTime,Integer limit) throws IOException {
  try {
    List<UserTrade> trades=new ArrayList<>();
    if (currencyPair == null) {
      HashMap<String,PoloniexUserTrade[]> poloniexUserTrades=returnTradeHistory(startTime,endTime,limit);
      if (poloniexUserTrades != null) {
        for (        Map.Entry<String,PoloniexUserTrade[]> mapEntry : poloniexUserTrades.entrySet()) {
          currencyPair=PoloniexUtils.toCurrencyPair(mapEntry.getKey());
          for (          PoloniexUserTrade poloniexUserTrade : mapEntry.getValue()) {
            trades.add(PoloniexAdapters.adaptPoloniexUserTrade(poloniexUserTrade,currencyPair));
          }
        }
      }
    }
 else {
      PoloniexUserTrade[] poloniexUserTrades=returnTradeHistory(currencyPair,startTime,endTime,limit);
      if (poloniexUserTrades != null) {
        for (        PoloniexUserTrade poloniexUserTrade : poloniexUserTrades) {
          trades.add(PoloniexAdapters.adaptPoloniexUserTrade(poloniexUserTrade,currencyPair));
        }
      }
    }
    return new UserTrades(trades,TradeSortType.SortByTimestamp);
  }
 catch (  PoloniexException e) {
    throw PoloniexErrorAdapter.adapt(e);
  }
}
