private static LimitOrder processOrderEntry(Map.Entry<String,MercadoBitcoinUserOrdersEntry> entry,CurrencyPair currencyPair){
  String id=entry.getKey();
  MercadoBitcoinUserOrdersEntry userOrdersEntry=entry.getValue();
  String type=userOrdersEntry.getType();
  OrderType orderType=toOrderType(type);
  BigDecimal price=userOrdersEntry.getPrice();
  BigDecimal volume=userOrdersEntry.getVolume();
  long time=userOrdersEntry.getCreated() * 1000L;
  return new LimitOrder(orderType,volume,currencyPair,id,new Date(time),price);
}
