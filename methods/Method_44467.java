public Map<String,KrakenTrade> queryKrakenTrades(boolean includeTrades,String... transactionIds) throws IOException {
  KrakenQueryTradeResult result=kraken.queryTrades(includeTrades,createDelimitedString(transactionIds),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory());
  return checkResult(result);
}
