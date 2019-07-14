public static void setKrakenAssetPairs(Map<String,KrakenAssetPair> pairs){
  if (assetPairMap.isEmpty()) {
    for (    Map.Entry<String,KrakenAssetPair> entry : pairs.entrySet()) {
      if (!entry.getKey().endsWith(".d")) {
        CurrencyPair pair=new CurrencyPair(translateKrakenCurrencyCode(entry.getValue().getBase()),translateKrakenCurrencyCode(entry.getValue().getQuote()));
        assetPairMap.put(entry.getKey(),pair);
        assetPairMapReverse.put(pair,entry.getKey());
      }
    }
  }
}
