public static UserTrade adaptArchivedOrder(CexIOArchivedOrder cexIOArchivedOrder){
  try {
    Date timestamp=fromISODateString(cexIOArchivedOrder.time);
    OrderType orderType=cexIOArchivedOrder.type.equals("sell") ? OrderType.ASK : OrderType.BID;
    BigDecimal originalAmount=cexIOArchivedOrder.amount;
    CurrencyPair currencyPair=new CurrencyPair(cexIOArchivedOrder.symbol1,cexIOArchivedOrder.symbol2);
    BigDecimal price=cexIOArchivedOrder.averageExecutionPrice;
    String id=cexIOArchivedOrder.id;
    String orderId=cexIOArchivedOrder.orderId;
    Currency feeCcy=cexIOArchivedOrder.feeCcy == null ? null : Currency.getInstance(cexIOArchivedOrder.feeCcy);
    BigDecimal fee=cexIOArchivedOrder.feeValue;
    return new UserTrade(orderType,originalAmount,currencyPair,price,timestamp,id,orderId,fee,feeCcy);
  }
 catch (  InvalidFormatException e) {
    throw new IllegalStateException("Cannot format date " + cexIOArchivedOrder.time,e);
  }
}
