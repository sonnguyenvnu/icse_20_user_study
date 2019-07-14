public PoloniexUserTrade[] returnTradeHistory(CurrencyPair currencyPair,Long startTime,Long endTime,Integer limit) throws IOException {
  return poloniexAuthenticated.returnTradeHistory(apiKey,signatureCreator,exchange.getNonceFactory(),PoloniexUtils.toPairString(currencyPair),startTime,endTime,limit);
}
