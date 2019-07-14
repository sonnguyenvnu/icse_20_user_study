public BitbayTradeResponse placeBitbayOrder(LimitOrder order) throws IOException {
  String currency=order.getCurrencyPair().base.toString();
  String paymentCurrency=order.getCurrencyPair().counter.toString();
  String type=order.getType() == Order.OrderType.ASK ? "ask" : "bid";
  BitbayTradeResponse response=bitbayAuthenticated.trade(apiKey,sign,exchange.getNonceFactory(),type,currency,order.getOriginalAmount(),paymentCurrency,order.getLimitPrice());
  checkError(response);
  return response;
}
