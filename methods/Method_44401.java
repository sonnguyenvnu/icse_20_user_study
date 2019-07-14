public ItBitOrder placeItBitLimitOrder(LimitOrder limitOrder) throws IOException {
  String side=limitOrder.getType().equals(OrderType.BID) ? "buy" : "sell";
  CurrencyPair exchangePair=ItBitAdapters.adaptCurrencyPairToExchange(limitOrder.getCurrencyPair());
  String amount=ItBitAdapters.formatCryptoAmount(limitOrder.getOriginalAmount());
  String price=ItBitAdapters.formatFiatAmount(limitOrder.getLimitPrice());
  ItBitOrder postOrder=itBitAuthenticated.postOrder(signatureCreator,new Date().getTime(),exchange.getNonceFactory(),walletId,new ItBitPlaceOrderRequest(side,"limit",exchangePair.base.getCurrencyCode(),amount,price,exchangePair.base.getCurrencyCode() + exchangePair.counter.getCurrencyCode()));
  return postOrder;
}
