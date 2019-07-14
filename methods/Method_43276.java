public List<BitmexMarginAccount> getBitmexMarginAccountsStatus() throws ExchangeException {
  return updateRateLimit(() -> bitmex.getMarginAccountsStatus(apiKey,exchange.getNonceFactory(),signatureCreator));
}
