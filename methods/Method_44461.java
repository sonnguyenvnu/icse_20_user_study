public KrakenAssetPairs getKrakenAssetPairs(CurrencyPair... currencyPairs) throws IOException {
  KrakenAssetPairsResult assetPairsResult=kraken.getAssetPairs(delimitAssetPairs(currencyPairs));
  return new KrakenAssetPairs(checkResult(assetPairsResult));
}
