PoloniexUserTrade[] returnOrderTrades(String orderId) throws IOException {
  return poloniexAuthenticated.returnOrderTrades(apiKey,signatureCreator,exchange.getNonceFactory(),orderId);
}
