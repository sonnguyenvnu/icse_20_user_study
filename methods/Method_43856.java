public CoinmateTradeResponse sellCoinmateLimit(BigDecimal amount,BigDecimal price,String currencyPair,BigDecimal stopPrice,Integer hidden,Integer immediateOrCancel,Integer trailing) throws IOException {
  CoinmateTradeResponse response=coinmateAuthenticated.sellLimit(exchange.getExchangeSpecification().getApiKey(),exchange.getExchangeSpecification().getUserName(),signatureCreator,exchange.getNonceFactory(),amount,price,currencyPair,stopPrice,hidden,immediateOrCancel,trailing);
  throwExceptionIfError(response);
  return response;
}
