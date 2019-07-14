public static UserTrade adaptTrade(Map<String,String> tradeDatum,CurrencyPair currencyPair){
  Order.OrderType type=adaptOrderType(tradeDatum);
  BigDecimal amount=new BigDecimal(tradeDatum.get("quantity"));
  BigDecimal price=new BigDecimal(tradeDatum.get("price"));
  Date date=DateUtils.fromUnixTime(Long.valueOf(tradeDatum.get("date")));
  String tradeId=tradeDatum.get("trade_id");
  String orderId=tradeDatum.get("order_id");
  return new UserTrade(type,amount,currencyPair,price,date,tradeId,orderId,null,null);
}
