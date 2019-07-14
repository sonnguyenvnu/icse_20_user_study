public static ExchangeMetaData adaptMetadata(List<ZaifMarket> markets){
  Map<CurrencyPair,CurrencyPairMetaData> pairMeta=new HashMap<>();
  for (  ZaifMarket zaifMarket : markets) {
    pairMeta.put(zaifMarket.getName(),new CurrencyPairMetaData(null,null,null,null,null));
  }
  return new ExchangeMetaData(pairMeta,null,null,null,null);
}
