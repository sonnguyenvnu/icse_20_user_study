public QuadrigaCxOrder sellQuadrigaCxOrder(CurrencyPair currencyPair,BigDecimal originalAmount,BigDecimal price) throws IOException {
  return quadrigacxAuthenticated.sell(QuadrigaCxUtils.currencyPairToBook(currencyPair),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),originalAmount,price);
}
