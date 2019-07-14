public static UserTrades adaptTrades(BTCTradeOrder[] btcTradeOrders,BTCTradeOrder[] btcTradeOrderDetails){
  List<UserTrade> trades=new ArrayList<>();
  for (int i=0; i < btcTradeOrders.length; i++) {
    BTCTradeOrder order=btcTradeOrders[i];
    CurrencyPair currencyPair=adaptCurrencyPair(order.getCoin());
    if (currencyPair != null) {
      BTCTradeOrder orderDetail=btcTradeOrderDetails[i];
      for (      org.knowm.xchange.btctrade.dto.trade.BTCTradeTrade trade : orderDetail.getTrades()) {
        trades.add(adaptTrade(order,trade,currencyPair));
      }
    }
  }
  return new UserTrades(trades,TradeSortType.SortByTimestamp);
}
