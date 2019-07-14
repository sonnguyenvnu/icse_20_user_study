public static void setBxAssetPairs(Map<String,BxAssetPair> pairs){
  for (  String id : pairs.keySet()) {
    if (pairs.get(id).isActive()) {
      setAsset(pairs.get(id).getPrimaryCurrency());
      setAsset(pairs.get(id).getSecondaryCurrency());
      CurrencyPair pair=new CurrencyPair(pairs.get(id).getPrimaryCurrency(),pairs.get(id).getSecondaryCurrency());
      assetPairMap.put(id,pair);
      assetPairMapReverse.put(pair,id);
    }
  }
}
