public BitMarketHistoryOperationsResponse getBitMarketOperationHistory(TradeHistoryParams params) throws IOException, ExchangeException {
  CurrencyPair currencyPair=CurrencyPair.BTC_PLN;
  int count=1000;
  long offset=0;
  if (params instanceof TradeHistoryParamCurrencyPair) {
    currencyPair=((TradeHistoryParamCurrencyPair)params).getCurrencyPair();
  }
  if (params instanceof TradeHistoryParamOffset) {
    offset=((TradeHistoryParamOffset)params).getOffset();
  }
  if (params instanceof BitMarketHistoryParams) {
    count=((BitMarketHistoryParams)params).getCount();
  }
  BitMarketHistoryOperationsResponse response=bitMarketAuthenticated.history(apiKey,sign,exchange.getNonceFactory(),currencyPair.base.getCurrencyCode(),count,offset);
  BitMarketHistoryOperationsResponse response2=bitMarketAuthenticated.history(apiKey,sign,exchange.getNonceFactory(),currencyPair.counter.getCurrencyCode(),count,offset);
  if (!response.getSuccess() || !response2.getSuccess()) {
    throw new ExchangeException(String.format("%d: %s",response.getError(),response.getErrorMsg()));
  }
  int combinedTotal=response.getData().getTotal() + response2.getData().getTotal();
  ArrayList<BitMarketHistoryOperation> combinedOperations=new ArrayList<>(combinedTotal);
  combinedOperations.addAll(response.getData().getOperations());
  combinedOperations.addAll(response2.getData().getOperations());
  BitMarketHistoryOperationsResponse combinedResponse=new BitMarketHistoryOperationsResponse(true,new BitMarketHistoryOperations(combinedTotal,response.getData().getStart(),response.getData().getCount() * 2,combinedOperations),response2.getLimit(),0,null);
  return combinedResponse;
}
