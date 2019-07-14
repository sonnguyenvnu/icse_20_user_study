public List<CmcTicker> getCmcLatestDataForAllCurrencies() throws IOException {
  CmcTickerListResponse response=null;
  try {
    response=cmcAuthenticated.getLatestListing(apiKey,1,5000,Currency.USD.getCurrencyCode(),"symbol","asc","all");
  }
 catch (  HttpStatusIOException ex) {
    CmcErrorAdapter.adapt(ex);
  }
  return response.getData();
}
