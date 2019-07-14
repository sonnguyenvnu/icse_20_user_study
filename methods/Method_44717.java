public PaymiumBalance getPaymiumBalances() throws IOException {
  return paymiumAuthenticated.getBalance(apiKey,signatureCreator,exchange.getNonceFactory());
}
