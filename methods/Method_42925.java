public static ExchangeMetaData adaptMetadata(List<BiboxMarket> markets){
  Map<CurrencyPair,CurrencyPairMetaData> pairMeta=new HashMap<>();
  for (  BiboxMarket biboxMarket : markets) {
    pairMeta.put(new CurrencyPair(biboxMarket.getCoinSymbol(),biboxMarket.getCurrencySymbol()),new CurrencyPairMetaData(null,null,null,null,null));
  }
  return new ExchangeMetaData(pairMeta,null,null,null,null);
}
