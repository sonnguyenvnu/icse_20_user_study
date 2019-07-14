public static UserTrades adaptTransactionHistory(CoinmateTransactionHistory coinmateTradeHistory){
  List<UserTrade> trades=new ArrayList<>(coinmateTradeHistory.getData().size());
  for (  CoinmateTransactionHistoryEntry entry : coinmateTradeHistory.getData()) {
    Order.OrderType orderType;
    String transactionType=entry.getTransactionType();
switch (transactionType) {
case "BUY":
case "QUICK_BUY":
      orderType=Order.OrderType.BID;
    break;
case "SELL":
case "QUICK_SELL":
  orderType=Order.OrderType.ASK;
break;
default :
continue;
}
UserTrade trade=new UserTrade(orderType,entry.getAmount(),CoinmateUtils.getPair(entry.getAmountCurrency() + "_" + entry.getPriceCurrency()),entry.getPrice(),new Date(entry.getTimestamp()),Long.toString(entry.getTransactionId()),Long.toString(entry.getOrderId()),entry.getFee(),Currency.getInstance(entry.getFeeCurrency()));
trades.add(trade);
}
return new UserTrades(trades,Trades.TradeSortType.SortByTimestamp);
}
