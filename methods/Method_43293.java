@Nonnull public BitmexPosition updateLeveragePosition(String symbol,BigDecimal leverage) throws ExchangeException {
  return updateRateLimit(() -> bitmex.updateLeveragePosition(apiKey,exchange.getNonceFactory(),signatureCreator,symbol,leverage));
}
