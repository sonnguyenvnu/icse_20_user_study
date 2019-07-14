@Nonnull public List<BitmexPrivateOrder> replaceOrderBulk(@Nonnull Collection<ReplaceOrderCommand> commands) throws ExchangeException {
  String s=ObjectMapperHelper.toCompactJSON(commands);
  return updateRateLimit(() -> bitmex.replaceOrderBulk(apiKey,exchange.getNonceFactory(),signatureCreator,s));
}
