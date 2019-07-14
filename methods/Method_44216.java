public static OpenOrders convertOpenOrders(List<EXXOrder> exxOpenOrders,CurrencyPair currencyPair){
  List<LimitOrder> openOrders=new LinkedList<>();
  for (  EXXOrder exxOrder : exxOpenOrders) {
    openOrders.add(new LimitOrder.Builder(convertType(exxOrder.getType()),currencyPair).id(exxOrder.getId()).timestamp(new Date(exxOrder.getTradeDate())).limitPrice(exxOrder.getPrice()).originalAmount(exxOrder.getTotalAmount()).build());
  }
  return new OpenOrders(openOrders);
}
