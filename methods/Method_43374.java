public static ExchangeMetaData adaptMetaData(List<BityPair> rawSymbols,ExchangeMetaData metaData){
  List<CurrencyPair> currencyPairs=BityAdapters.adaptCurrencyPairs(rawSymbols);
  Map<CurrencyPair,CurrencyPairMetaData> pairsMap=metaData.getCurrencyPairs();
  Map<Currency,CurrencyMetaData> currenciesMap=metaData.getCurrencies();
  for (  CurrencyPair c : currencyPairs) {
    if (!pairsMap.containsKey(c)) {
      pairsMap.put(c,null);
    }
    if (!currenciesMap.containsKey(c.base)) {
      currenciesMap.put(c.base,null);
    }
    if (!currenciesMap.containsKey(c.counter)) {
      currenciesMap.put(c.counter,null);
    }
  }
  return metaData;
}
