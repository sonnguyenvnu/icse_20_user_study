public PoloniexOpenOrder[] returnOpenOrders(CurrencyPair currencyPair) throws IOException {
  return poloniexAuthenticated.returnOpenOrders(apiKey,signatureCreator,exchange.getNonceFactory(),PoloniexUtils.toPairString(currencyPair));
}
