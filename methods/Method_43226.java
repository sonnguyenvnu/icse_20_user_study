public static UserTrade adoptUserTrade(BitmexPrivateExecution exec){
  CurrencyPair pair=BitmexAdapters.adaptSymbolToCurrencyPair(exec.symbol);
  OrderType orderType=convertType(exec.side);
  return orderType == null ? null : new UserTrade.Builder().id(exec.execID).orderId(exec.orderID).currencyPair(pair).originalAmount(exec.lastQty).price(exec.lastPx).feeAmount(exec.commission.multiply(exec.lastQty)).feeCurrency(pair.counter.equals(Currency.USD) ? pair.counter : pair.base).timestamp(exec.timestamp).type(orderType).build();
}
