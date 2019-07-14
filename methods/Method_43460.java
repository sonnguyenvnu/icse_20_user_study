public BTCMarketsTradeHistory getBTCMarketsUserTransactions(CurrencyPair currencyPair,Integer limit,Long since) throws IOException {
  BTCMarketsOpenOrdersAndTradeHistoryRequest request=new BTCMarketsOpenOrdersAndTradeHistoryRequest(currencyPair.counter.getCurrencyCode(),currencyPair.base.getCurrencyCode(),limit,since);
  return btcm.getTradeHistory(exchange.getExchangeSpecification().getApiKey(),nonceFactory,signer,request);
}
