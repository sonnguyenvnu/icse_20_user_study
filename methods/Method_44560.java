@Override public void remoteInit() throws IOException {
  try {
    List<LivecoinRestriction> products=((LivecoinMarketDataServiceRaw)marketDataService).getRestrictions();
    exchangeMetaData=LivecoinAdapters.adaptToExchangeMetaData(exchangeMetaData,products);
  }
 catch (  LivecoinException e) {
    throw LivecoinErrorAdapter.adapt(e);
  }
}
