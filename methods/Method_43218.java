public List<BitMarketHistoryOperation> accountHistory(Currency currency,int count,int offset) throws IOException {
  BitMarketHistoryOperationsResponse history=bitMarketAuthenticated.history(apiKey,sign,exchange.getNonceFactory(),currency.getCurrencyCode(),count,offset);
  BitMarketHistoryOperations data=history.getData();
  return data.getOperations();
}
