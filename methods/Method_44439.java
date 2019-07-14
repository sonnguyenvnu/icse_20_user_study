@Override public void remoteInit() throws IOException {
  KrakenAssetPairs assetPairs=((KrakenMarketDataServiceRaw)marketDataService).getKrakenAssetPairs();
  KrakenAssets assets=((KrakenMarketDataServiceRaw)marketDataService).getKrakenAssets();
  KrakenUtils.clearAssets();
  exchangeMetaData=KrakenAdapters.adaptToExchangeMetaData(exchangeMetaData,assetPairs.getAssetPairMap(),assets.getAssetPairMap());
}
