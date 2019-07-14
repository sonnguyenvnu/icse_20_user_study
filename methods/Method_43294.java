public HttpResponseAwareList<BitmexPrivateExecution> getExecutions(String symbol,String filter,String columns,Integer count,Long start,Boolean reverse,Date startTime,Date endTime) throws ExchangeException {
  return updateRateLimit(() -> bitmex.getExecutions(apiKey,exchange.getNonceFactory(),signatureCreator,symbol,filter,columns,count,start,reverse,startTime,endTime));
}
