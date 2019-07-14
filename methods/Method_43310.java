@Override public void remoteInit() throws IOException, ExchangeException {
  BitstampMarketDataServiceRaw dataService=(BitstampMarketDataServiceRaw)this.marketDataService;
  BitstampPairInfo[] bitstampPairInfos=dataService.getTradingPairsInfo();
  exchangeMetaData=BitstampAdapters.adaptMetaData(Arrays.asList(bitstampPairInfos),exchangeMetaData);
}
