public PoloniexAccountBalance[] returnAllAvailableAccountBalances() throws IOException {
  return poloniexAuthenticated.returnAvailableAccountBalances(apiKey,signatureCreator,exchange.getNonceFactory());
}
