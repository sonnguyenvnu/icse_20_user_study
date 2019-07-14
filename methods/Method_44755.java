public HashMap<String,PoloniexUserTrade[]> returnTradeHistory(Long startTime,Long endTime,Integer limit) throws IOException {
  String ignore=null;
  return poloniexAuthenticated.returnTradeHistory(apiKey,signatureCreator,exchange.getNonceFactory(),"all",startTime,endTime,limit,ignore);
}
