IndependentReserveTransactionsResponse getTransactions(String account,Date fromTimestampUtc,Date toTimestampUt,IndependentReserveTransaction.Type[] txTypes,int pageIndex,int pageSize) throws IndependentReserveHttpStatusException, IOException {
  Long nonce=exchange.getNonceFactory().createValue();
  IndependentReserveTransactionsRequest req=new IndependentReserveTransactionsRequest(exchange.getExchangeSpecification().getApiKey(),nonce,account,fromTimestampUtc,toTimestampUt,txTypes,pageIndex,pageSize);
  req.setSignature(signatureCreator.digestParamsToString(ExchangeEndpoint.GET_TRANSACTIONS,nonce,req.getParameters()));
  return independentReserveAuthenticated.getTransactions(req);
}
