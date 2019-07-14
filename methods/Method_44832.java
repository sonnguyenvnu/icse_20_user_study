public RippleAccountBalances getRippleAccountBalances() throws IOException {
  return getRippleAccountBalances(exchange.getExchangeSpecification().getApiKey());
}
