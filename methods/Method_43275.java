public BitmexWallet getBitmexWallet(Currency... ccy) throws ExchangeException {
  return updateRateLimit(() -> bitmex.getWallet(apiKey,exchange.getNonceFactory(),signatureCreator));
}
