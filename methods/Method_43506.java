private static CurrencyPairMetaData adaptCurrencyPairMetaData(BxAssetPair assetPair){
  return new CurrencyPairMetaData(null,assetPair.getPrimaryMin(),null,0,null);
}
