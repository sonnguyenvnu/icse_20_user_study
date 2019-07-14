public static ExchangeMetaData toMetaData(DSXExchangeInfo dsxExchangeInfo,DSXMetaData dsxMetaData){
  Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=new HashMap<>();
  Map<Currency,CurrencyMetaData> currencies=new HashMap<>();
  if (dsxExchangeInfo != null) {
    for (    Entry<String,DSXPairInfo> e : dsxExchangeInfo.getPairs().entrySet()) {
      String marketName=e.getKey();
      CurrencyPair pair=adaptCurrencyPair(marketName);
      CurrencyPairMetaData marketMetaData=toMarketMetaData(e.getValue());
      currencyPairs.put(pair,marketMetaData);
      addCurrencyMetaData(pair.base,currencies,dsxMetaData);
      addCurrencyMetaData(pair.counter,currencies,dsxMetaData);
    }
  }
  RateLimit[] publicRateLimits=new RateLimit[]{new RateLimit(dsxMetaData.publicInfoCacheSeconds,1,TimeUnit.SECONDS)};
  return new ExchangeMetaData(currencyPairs,currencies,publicRateLimits,null,false);
}
