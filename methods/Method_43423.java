public static Trades adaptBleutradeMarketHistory(List<BleutradeTrade> bleutradeTrades,CurrencyPair currencyPair){
  List<Trade> trades=new ArrayList<>();
  for (  BleutradeTrade bleutradeTrade : bleutradeTrades) {
    Trade.Builder builder=new Trade.Builder();
    builder.currencyPair(currencyPair);
    builder.price(bleutradeTrade.getPrice());
    builder.timestamp(BleutradeUtils.toDate(bleutradeTrade.getTimeStamp()));
    builder.originalAmount(bleutradeTrade.getQuantity());
    builder.type(bleutradeTrade.getOrderType().equals("BUY") ? OrderType.BID : OrderType.ASK);
    trades.add(builder.build());
  }
  return new Trades(trades,TradeSortType.SortByTimestamp);
}
