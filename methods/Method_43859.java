public CoinmateTradeResponse buyCoinmateInstant(BigDecimal total,String currencyPair) throws IOException {
  CoinmateTradeResponse response=coinmateAuthenticated.buyInstant(exchange.getExchangeSpecification().getApiKey(),exchange.getExchangeSpecification().getUserName(),signatureCreator,exchange.getNonceFactory(),total,currencyPair);
  throwExceptionIfError(response);
  return response;
}
