@Override public void remoteInit() throws IOException, ExchangeException {
  Map<String,BxAssetPair> assetPairs=((BxMarketDataServiceRaw)marketDataService).getBxAssetPairs();
  exchangeMetaData=BxAdapters.adaptToExchangeMetaData(assetPairs);
}
