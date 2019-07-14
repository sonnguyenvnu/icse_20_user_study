@Override public void remoteInit() throws IOException, ExchangeException {
  exchangeMetaData=((BiboxMarketDataService)marketDataService).getMetadata();
}
