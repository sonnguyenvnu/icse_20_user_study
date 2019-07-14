public Pagination<TradeResponse> getKucoinFills(String symbol,int page,int pageSize) throws IOException {
  checkAuthenticated();
  return classifyingExceptions(() -> fillApi.queryTrades(apiKey,digest,nonceFactory,passphrase,symbol,null,null,null,null,null,pageSize,page));
}
