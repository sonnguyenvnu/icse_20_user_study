@Override public void remoteInit() throws IOException, ExchangeException {
  try {
    BityMarketDataServiceRaw dataService=(BityMarketDataServiceRaw)this.marketDataService;
    List<BityPair> bityPairs=dataService.getBityPairs();
    exchangeMetaData=BityAdapters.adaptMetaData(bityPairs,exchangeMetaData);
    token=((BityAccountService)accountService).createToken();
    marketDataService.getTickers(null);
  }
 catch (  BityException e) {
    throw BityErrorAdapter.adapt(e);
  }
}
