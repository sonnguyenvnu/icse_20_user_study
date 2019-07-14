public Map<String,CmcTicker> getCmcLatestQuote(CurrencyPair currencyPair) throws IOException {
  CmcTickerResponse response=null;
  try {
    response=cmcAuthenticated.getLatestQuotes(apiKey,currencyPair.base.getCurrencyCode(),currencyPair.counter.getCurrencyCode());
  }
 catch (  HttpStatusIOException ex) {
    CmcErrorAdapter.adapt(ex);
  }
  return response.getData();
}
