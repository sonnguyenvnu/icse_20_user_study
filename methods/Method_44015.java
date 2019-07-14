public BatchOrderResult sendCryptoFacilitiesBatchOrder(List<OrderCommand> commands) throws IOException {
  BatchOrderResult ord=cryptoFacilities.batchOrder(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),new BatchOrder(commands));
  if (ord.isSuccess()) {
    return ord;
  }
 else {
    throw new ExchangeException("Error sending CF batch order: " + ord.getError());
  }
}
