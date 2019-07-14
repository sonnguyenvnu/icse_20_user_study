public static ExchangeMetaData adaptMetaData(List<CurrencyPair> currencyPairs,ExchangeMetaData metaData){
  Map<CurrencyPair,CurrencyPairMetaData> pairsMap=metaData.getCurrencyPairs();
  Map<Currency,CurrencyMetaData> currenciesMap=metaData.getCurrencies();
  pairsMap.keySet().retainAll(currencyPairs);
  Set<Currency> currencies=currencyPairs.stream().flatMap(pair -> Stream.of(pair.base,pair.counter)).collect(Collectors.toSet());
  currenciesMap.keySet().retainAll(currencies);
  for (  CurrencyPair c : currencyPairs) {
    if (!pairsMap.containsKey(c)) {
      pairsMap.put(c,null);
    }
    if (!currenciesMap.containsKey(c.base)) {
      currenciesMap.put(c.base,new CurrencyMetaData(2,null));
    }
    if (!currenciesMap.containsKey(c.counter)) {
      currenciesMap.put(c.counter,new CurrencyMetaData(2,null));
    }
  }
  return metaData;
}
