public BitstampRippleDepositAddress getRippleDepositAddress() throws IOException {
  return bitstampAuthenticated.getRippleDepositAddress(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
}
