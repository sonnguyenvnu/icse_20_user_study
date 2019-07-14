@Override public void remoteInit() throws IOException, ExchangeException {
  try {
    Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=exchangeMetaData.getCurrencyPairs();
    Map<Currency,CurrencyMetaData> currencies=exchangeMetaData.getCurrencies();
    List<CurrencyPair> currencyPairList=getExchangeSymbols();
    List<FeeTier> feeTiers=new ArrayList<>();
    feeTiers.add(new FeeTier(BigDecimal.ZERO,new Fee(BigDecimal.ZERO,BigDecimal.ZERO)));
    LykkeMarketDataService marketDataService=(LykkeMarketDataService)this.marketDataService;
    List<LykkeAssetPair> assetPairList=marketDataService.getAssetPairs();
    for (    LykkeAssetPair lykkeAssetPair : assetPairList) {
      CurrencyPair currencyPair=new CurrencyPair(lykkeAssetPair.getName().split("/")[0],lykkeAssetPair.getQuotingAssetId());
      CurrencyPairMetaData currencyPairMetaData=new CurrencyPairMetaData(null,null,null,lykkeAssetPair.getAccuracy(),feeTiers.toArray(new FeeTier[feeTiers.size()]));
      currencyPairs.put(currencyPair,currencyPairMetaData);
      currencyPairList.add(currencyPair);
    }
    for (    LykkeAsset lykkeAsset : marketDataService.getLykkeAssets()) {
      if (lykkeAsset.getSymbol() != null) {
        Currency currency=new Currency(lykkeAsset.getSymbol());
        CurrencyMetaData currencyMetaData=new CurrencyMetaData(lykkeAsset.getAccuracy(),null);
        currencies.put(currency,currencyMetaData);
      }
    }
  }
 catch (  Exception e) {
    logger.warn("An exception occurred while loading the metadata",e);
  }
}
