public Long tradePairId(CurrencyPair currencyPair){
  CryptopiaTradePair cryptopiaTradePair=lookupByCcyPair.get(currencyPair);
  if (cryptopiaTradePair == null) {
    throw new RuntimeException("Pair not supported by Cryptopia exchange: " + currencyPair);
  }
  return cryptopiaTradePair.getId();
}
