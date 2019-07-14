@Override public void remoteInit() throws IOException, ExchangeException {
  logger.info("Attempting remoteInit for {}",getExchangeSpecification().getExchangeName());
  final CexIOCurrencyLimits currencyLimits=((CexIOMarketDataService)this.marketDataService).getCurrencyLimits();
  final Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=getExchangeMetaData().getCurrencyPairs();
  for (  CexIOCurrencyLimits.Pair pair : currencyLimits.getData().getPairs()) {
    CurrencyPair currencyPair=new CurrencyPair(pair.getSymbol1(),pair.getSymbol2());
    CurrencyPairMetaData metaData=new CurrencyPairMetaData(null,pair.getMinLotSize(),pair.getMaxLotSize(),null,null);
    currencyPairs.merge(currencyPair,metaData,(oldMetaData,newMetaData) -> new CurrencyPairMetaData(oldMetaData.getTradingFee(),newMetaData.getMinimumAmount(),newMetaData.getMaximumAmount() != null ? newMetaData.getMaximumAmount() : oldMetaData.getMaximumAmount(),oldMetaData.getPriceScale(),newMetaData.getFeeTiers() != null ? newMetaData.getFeeTiers() : oldMetaData.getFeeTiers()));
  }
  logger.info("remoteInit successful for {}",getExchangeSpecification().getExchangeName());
}
