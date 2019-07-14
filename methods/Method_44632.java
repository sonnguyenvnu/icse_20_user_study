public static UserTrades toUserTrades(CurrencyPair pair,MercadoBitcoinBaseTradeApiResult<MercadoBitcoinUserOrders> orders){
  List<UserTrade> result=new LinkedList<>();
  for (  Map.Entry<String,MercadoBitcoinUserOrdersEntry> e : orders.getTheReturn().entrySet()) {
    String orderId=e.getKey();
    MercadoBitcoinUserOrdersEntry order=e.getValue();
    OrderType type=toOrderType(order.getType());
    for (    Map.Entry<String,OperationEntry> f : order.getOperations().entrySet()) {
      String txId=f.getKey();
      OperationEntry op=f.getValue();
      result.add(new UserTrade.Builder().currencyPair(pair).id(txId).orderId(orderId).price(op.getPrice()).timestamp(fromUnixTime(op.getCreated())).originalAmount(op.getVolume()).type(type).build());
    }
  }
  return new UserTrades(result,Trades.TradeSortType.SortByID);
}
