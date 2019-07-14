public BitsoOrder sellBitsoOrder(BigDecimal originalAmount,BigDecimal price) throws IOException {
  return bitsoAuthenticated.sell(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),originalAmount,price);
}
