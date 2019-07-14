@Override public void remoteInit() throws IOException, ExchangeException {
  ((ExmoMarketDataService)marketDataService).updateMetadata(exchangeMetaData);
}
