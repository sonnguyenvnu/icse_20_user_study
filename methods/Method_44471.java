public Map<String,KrakenOrder> getOrders(String... orderIds) throws IOException {
  String orderIdsString=String.join(",",orderIds);
  KrakenQueryOrderResult krakenOrderResult=kraken.queryOrders(false,null,orderIdsString,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return checkResult(krakenOrderResult);
}
