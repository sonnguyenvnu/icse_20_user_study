public static ExchangeMetaData toMetaData(WexExchangeInfo wexExchangeInfo,WexMetaData wexMetaData){
  Map<CurrencyPair,CurrencyPairMetaData> currencyPairs=new HashMap<>(wexMetaData.getCurrencyPairs());
  Map<Currency,CurrencyMetaData> currencies=new HashMap<>(wexMetaData.getCurrencies());
  if (wexExchangeInfo != null) {
    for (    Entry<String,WexPairInfo> e : wexExchangeInfo.getPairs().entrySet()) {
      CurrencyPair pair=adaptCurrencyPair(e.getKey());
      CurrencyPairMetaData marketMetaData=toMarketMetaData(e.getValue(),wexMetaData);
      currencyPairs.put(pair,marketMetaData);
      addCurrencyMetaData(pair.base,currencies,wexMetaData);
      addCurrencyMetaData(pair.counter,currencies,wexMetaData);
    }
  }
  RateLimit[] publicRateLimits=new RateLimit[]{new RateLimit(wexMetaData.publicInfoCacheSeconds,1,TimeUnit.SECONDS)};
  return new ExchangeMetaData(currencyPairs,currencies,publicRateLimits,null,false);
}
