private BitcointoyouOrderResponse createOrder(String action,LimitOrder limitOrder) throws IOException {
  try {
    String asset=limitOrder.getCurrencyPair().base.getSymbol();
    return bitcointoyouAuthenticated.createOrder(apiKey,exchange.getNonceFactory(),signatureCreator,asset,action,limitOrder.getOriginalAmount(),limitOrder.getLimitPrice());
  }
 catch (  BitcointoyouException e) {
    throw new ExchangeException(e.getError());
  }
}
