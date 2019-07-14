public static OpenOrders adaptPrivateOrders(ItBitOrder[] orders){
  if (orders.length <= 0) {
    return noOpenOrders;
  }
  List<LimitOrder> limitOrders=new ArrayList<>(orders.length);
  for (int i=0; i < orders.length; i++) {
    ItBitOrder itBitOrder=orders[i];
    String instrument=itBitOrder.getInstrument();
    CurrencyPair currencyPair=new CurrencyPair(instrument.substring(0,3),instrument.substring(3,6));
    OrderType orderType=itBitOrder.getSide().equals("buy") ? OrderType.BID : OrderType.ASK;
    Date timestamp=parseDate(itBitOrder.getCreatedTime());
    limitOrders.add(adaptOrder(itBitOrder.getAmount(),itBitOrder.getPrice(),currencyPair,itBitOrder.getId(),orderType,timestamp));
  }
  return new OpenOrders(limitOrders);
}
