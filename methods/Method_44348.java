@Override public void remoteInit() throws IOException, ExchangeException {
  HuobiAssetPair[] assetPairs=((HuobiMarketDataServiceRaw)marketDataService).getHuobiAssetPairs();
  HuobiAsset[] assets=((HuobiMarketDataServiceRaw)marketDataService).getHuobiAssets();
  exchangeMetaData=HuobiAdapters.adaptToExchangeMetaData(assetPairs,assets,exchangeMetaData);
}
