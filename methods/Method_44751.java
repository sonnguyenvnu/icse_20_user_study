public Map<String,PoloniexOpenOrder[]> returnOpenOrders() throws IOException {
  return poloniexAuthenticated.returnOpenOrders(apiKey,signatureCreator,exchange.getNonceFactory(),PoloniexAuthenticated.AllPairs.all);
}
