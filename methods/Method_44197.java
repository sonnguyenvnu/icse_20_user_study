public void updateMetadata(ExchangeMetaData exchangeMetaData) throws IOException {
  Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=exchangeMetaData.getCurrencyPairs();
  Map<Currency,CurrencyMetaData> currencies=exchangeMetaData.getCurrencies();
  Map<String,Map<String,String>> map=exmo.pairSettings();
  for (  String marketName : map.keySet()) {
    CurrencyPair currencyPair=adaptMarket(marketName);
    Map<String,String> data=map.get(marketName);
    Integer priceScale=null;
    BigDecimal tradingFee=null;
    if (currencyPairs.containsKey(currencyPair)) {
      priceScale=currencyPairs.get(currencyPair).getPriceScale();
      tradingFee=currencyPairs.get(currencyPair).getTradingFee();
    }
    CurrencyPairMetaData staticMeta=currencyPairs.get(currencyPair);
    CurrencyPairMetaData currencyPairMetaData=new CurrencyPairMetaData(tradingFee,new BigDecimal(data.get("min_quantity")),new BigDecimal(data.get("max_quantity")),priceScale,staticMeta != null ? staticMeta.getFeeTiers() : null);
    currencyPairs.put(currencyPair,currencyPairMetaData);
    if (!currencies.containsKey(currencyPair.base))     currencies.put(currencyPair.base,new CurrencyMetaData(8,null));
    if (!currencies.containsKey(currencyPair.counter))     currencies.put(currencyPair.counter,new CurrencyMetaData(8,null));
  }
}
