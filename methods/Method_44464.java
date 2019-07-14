public KrakenQueryOrderResult queryKrakenOrdersResult(boolean includeTrades,String userRef,String... transactionIds) throws IOException {
  KrakenQueryOrderResult krakenQueryOrderResult=kraken.queryOrders(includeTrades,userRef,createDelimitedString(transactionIds),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return krakenQueryOrderResult;
}
