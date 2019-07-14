@Override public void remoteInit() throws IOException, ExchangeException {
  BitflyerMarketDataServiceRaw dataService=(BitflyerMarketDataServiceRaw)this.marketDataService;
  List<BitflyerMarket> markets=dataService.getMarkets();
  exchangeMetaData=BitflyerAdapters.adaptMetaData(markets);
}
