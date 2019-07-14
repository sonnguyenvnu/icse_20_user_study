private static LimitOrder withAmount(LimitOrder limitOrder,BigDecimal tradeableAmount){
  OrderType type=limitOrder.getType();
  CurrencyPair currencyPair=limitOrder.getCurrencyPair();
  String id=limitOrder.getId();
  Date date=limitOrder.getTimestamp();
  BigDecimal limit=limitOrder.getLimitPrice();
  return new LimitOrder(type,tradeableAmount,currencyPair,id,date,limit);
}
