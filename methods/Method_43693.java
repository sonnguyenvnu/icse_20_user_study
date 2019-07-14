public static UserTrades adaptTradeHistory(CoinbaseProFill[] coinbaseExFills){
  List<UserTrade> trades=new ArrayList<>(coinbaseExFills.length);
  for (int i=0; i < coinbaseExFills.length; i++) {
    CoinbaseProFill fill=coinbaseExFills[i];
    OrderType type=fill.getSide().equals("buy") ? OrderType.BID : OrderType.ASK;
    CurrencyPair currencyPair=new CurrencyPair(fill.getProductId().replace('-','/'));
    UserTrade t=new UserTrade(type,fill.getSize(),currencyPair,fill.getPrice(),parseDate(fill.getCreatedAt()),String.valueOf(fill.getTradeId()),fill.getOrderId(),fill.getFee(),currencyPair.counter);
    trades.add(t);
  }
  return new UserTrades(trades,TradeSortType.SortByID);
}
