public Map<String,PoloniexMarginPostionResponse> returnAllMarginPositions() throws IOException {
  return poloniexAuthenticated.getMarginPosition(apiKey,signatureCreator,exchange.getNonceFactory(),PoloniexAuthenticated.AllPairs.all);
}
