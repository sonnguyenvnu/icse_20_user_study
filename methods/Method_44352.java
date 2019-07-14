public static void setHuobiAssetPairs(HuobiAssetPair[] huobiAssetPairs){
  for (  HuobiAssetPair entry : huobiAssetPairs) {
    CurrencyPair pair=new CurrencyPair(translateHuobiCurrencyCode(entry.getBaseCurrency()),translateHuobiCurrencyCode(entry.getQuoteCurrency()));
    if (pair.base != null && pair.counter != null) {
      assetPairMap.put(entry.getKey(),pair);
      assetPairMapReverse.put(pair,entry.getKey());
    }
  }
}
