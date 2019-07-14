public TheRockUserTrades getTheRockUserTrades(CurrencyPair currencyPair,Long sinceTradeId,Date after,Date before,int pageSize,int page) throws IOException {
  try {
    return theRockAuthenticated.trades(new TheRock.Pair(currencyPair),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getNonceFactory(),sinceTradeId,after,before,pageSize,page);
  }
 catch (  Throwable e) {
    throw new ExchangeException(e);
  }
}
