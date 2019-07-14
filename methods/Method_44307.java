public GlobitexExecutionReport globitexCancelOrder(String clientOrderId) throws IOException {
  try {
    return globitex.cancelOrder(exchange.getExchangeSpecification().getApiKey(),exchange.getNonceFactory(),signatureCreator,clientOrderId);
  }
 catch (  HttpStatusIOException e) {
    throw new ExchangeException(e.getHttpBody());
  }
}
