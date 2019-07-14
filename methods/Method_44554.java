public LiquiTrade placeLiquiLimitOrder(final LimitOrder order){
  final LiquiTradeType orderType=LiquiTradeType.fromOrderType(order.getType());
  final LiquiTradeResult trade=liquiAuthenticated.trade(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),"trade",new Liqui.Pairs(order.getCurrencyPair()),orderType.toString(),order.getLimitPrice().toPlainString(),order.getRemainingAmount().toPlainString());
  return checkResult(trade);
}
