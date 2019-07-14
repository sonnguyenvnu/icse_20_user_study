public BitsoOrder buyBitoOrder(BigDecimal originalAmount,BigDecimal price) throws IOException {
  return bitsoAuthenticated.buy(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),originalAmount,price);
}
