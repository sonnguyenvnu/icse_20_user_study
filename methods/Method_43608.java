public static ExchangeMetaData adaptMetadata(List<CobinhoodCurrencyPair> pairs){
  Map<CurrencyPair,CurrencyPairMetaData> pairMeta=new HashMap<>();
  for (  CobinhoodCurrencyPair pair : pairs) {
    pairMeta.put(new CurrencyPair(pair.getBaseCurrencyId(),pair.getQuoteCurrencyId()),new CurrencyPairMetaData(null,pair.getBaseMinSize(),pair.getBaseMaxSize(),null,null));
  }
  return new ExchangeMetaData(pairMeta,null,null,null,null);
}
