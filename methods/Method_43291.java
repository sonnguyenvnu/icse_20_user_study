@Nonnull public List<BitmexPrivateOrder> placeOrderBulk(@Nonnull Collection<PlaceOrderCommand> commands) throws ExchangeException {
  String s=ObjectMapperHelper.toCompactJSON(commands);
  return updateRateLimit(() -> bitmex.placeOrderBulk(apiKey,exchange.getNonceFactory(),signatureCreator,s));
}
