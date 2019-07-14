private static void setAsset(String currency){
  assetMap.put(currency,Currency.getInstance(currency));
  assetMapReverse.put(Currency.getInstance(currency),currency);
}
