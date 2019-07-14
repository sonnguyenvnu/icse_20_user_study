public CoinmateCancelOrderWithInfoResponse cancelCoinmateOrderWithInfo(String orderId) throws IOException {
  CoinmateCancelOrderWithInfoResponse response=coinmateAuthenticated.cancelOderWithInfo(exchange.getExchangeSpecification().getApiKey(),exchange.getExchangeSpecification().getUserName(),signatureCreator,exchange.getNonceFactory(),orderId);
  throwExceptionIfError(response);
  return response;
}
