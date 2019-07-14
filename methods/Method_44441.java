public static void setKrakenAssets(Map<String,KrakenAsset> assetSource){
  if (assetsMap.isEmpty()) {
    for (    Map.Entry<String,KrakenAsset> entry : assetSource.entrySet()) {
      assetsMap.put(entry.getKey(),Currency.getInstance(entry.getValue().getAltName()));
      assetsMapReverse.put(Currency.getInstance(entry.getValue().getAltName()),entry.getKey());
    }
  }
}
