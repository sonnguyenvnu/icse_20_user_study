public static void setHuobiAssets(HuobiAsset[] huobiAssets){
  for (  HuobiAsset entry : huobiAssets) {
    assetMap.put(entry.getAsset(),Currency.getInstance(entry.getAsset()));
    assetMapReverse.put(Currency.getInstance(entry.getAsset()),entry.getAsset());
  }
}
