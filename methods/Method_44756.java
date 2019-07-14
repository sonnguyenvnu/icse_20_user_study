public PoloniexMarginAccountResponse returnMarginAccountSummary() throws IOException {
  return poloniexAuthenticated.returnMarginAccountSummary(apiKey,signatureCreator,exchange.getNonceFactory());
}
