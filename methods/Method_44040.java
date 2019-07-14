@Override public void remoteInit() throws IOException, ExchangeException {
  List<CryptopiaCurrency> currencies=((CryptopiaMarketDataServiceRaw)marketDataService).getCryptopiaCurrencies();
  List<CryptopiaTradePair> tradePairs=((CryptopiaMarketDataServiceRaw)marketDataService).getCryptopiaTradePairs();
  Map<CurrencyPair,CryptopiaTradePair> lookupByCcyPair=new HashMap<>();
  Map<Long,CryptopiaTradePair> lookupById=new HashMap<>();
  for (  CryptopiaTradePair tradePair : tradePairs) {
    lookupByCcyPair.put(CurrencyPairDeserializer.getCurrencyPairFromString(tradePair.getLabel()),tradePair);
    lookupById.put(tradePair.getId(),tradePair);
  }
  this.lookupByCcyPair=lookupByCcyPair;
  this.lookupById=lookupById;
  exchangeMetaData=CryptopiaAdapters.adaptToExchangeMetaData(currencies,tradePairs);
}
