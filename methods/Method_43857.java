public CoinmateReplaceResponse coinmateReplaceByBuyLimit(String orderId,BigDecimal amount,BigDecimal price,String currencyPair,BigDecimal stopPrice,Integer hidden,Integer immediateOrCancel,Integer trailing) throws IOException {
  CoinmateReplaceResponse response=coinmateAuthenticated.replaceByBuyLimit(exchange.getExchangeSpecification().getApiKey(),exchange.getExchangeSpecification().getUserName(),signatureCreator,exchange.getNonceFactory(),amount,price,currencyPair,orderId,stopPrice,hidden,immediateOrCancel,trailing);
  throwExceptionIfError(response);
  return response;
}
