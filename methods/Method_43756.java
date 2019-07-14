@Override public void remoteInit(){
  Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=exchangeMetaData.getCurrencyPairs();
  CoindirectMarketDataService coindirectMarketDataService=(CoindirectMarketDataService)marketDataService;
  try {
    List<CoindirectMarket> coindirectMarketList=coindirectMarketDataService.getCoindirectMarkets(1000);
    for (    CoindirectMarket market : coindirectMarketList) {
      CurrencyPair currencyPair=CoindirectAdapters.toCurrencyPair(market.symbol);
      CurrencyPairMetaData staticMeta=currencyPairs.get(currencyPair);
      CurrencyPairMetaData adaptedMeta=new CurrencyPairMetaData(staticMeta.getTradingFee(),market.minimumQuantity,market.maximumQuantity,staticMeta.getPriceScale(),staticMeta.getFeeTiers());
      currencyPairs.put(currencyPair,adaptedMeta);
    }
  }
 catch (  IOException exception) {
  }
}
