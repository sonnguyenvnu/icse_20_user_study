@Override public void remoteInit() throws IOException, ExchangeException {
  this.exchangeMetaData=KucoinAdapters.adaptMetadata(this.exchangeMetaData,getMarketDataService().getKucoinSymbols());
}
