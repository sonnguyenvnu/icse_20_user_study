@Override public void remoteInit(){
  try {
    DSXMarketDataServiceV3 marketDataService=(DSXMarketDataServiceV3)this.marketDataService;
    dsxExchangeInfo=marketDataService.getDSXInfo();
    exchangeMetaData=DSXAdapters.toMetaData(dsxExchangeInfo,dsxMetaData);
  }
 catch (  Exception e) {
    logger.warn("An exception occurred while loading the metadata",e);
  }
}
