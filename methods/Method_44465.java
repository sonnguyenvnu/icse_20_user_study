public Map<String,KrakenOrder> queryKrakenOrders(boolean includeTrades,String userRef,String... transactionIds) throws IOException {
  KrakenQueryOrderResult result=kraken.queryOrders(includeTrades,userRef,createDelimitedString(transactionIds),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return checkResult(result);
}
