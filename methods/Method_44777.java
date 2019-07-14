public QuadrigaCxOrder buyQuadrigaCxOrder(CurrencyPair currencyPair,BigDecimal originalAmount,BigDecimal price) throws IOException {
  return quadrigacxAuthenticated.buy(QuadrigaCxUtils.currencyPairToBook(currencyPair),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),originalAmount,price);
}
