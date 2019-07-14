public BitstampDepositAddress getBitstampEthereumDepositAddress() throws IOException {
  try {
    final BitstampDepositAddress response=bitstampAuthenticated.getEthereumDepositAddress(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
    if (response.getError() != null) {
      throw new ExchangeException("Requesting Bitcoin deposit address failed: " + response.getError());
    }
    return response;
  }
 catch (  BitstampException e) {
    throw handleError(e);
  }
}
