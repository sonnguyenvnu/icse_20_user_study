public BitmexAccount getBitmexAccountInfo() throws ExchangeException {
  return updateRateLimit(() -> bitmex.getAccount(apiKey,exchange.getNonceFactory(),signatureCreator));
}
