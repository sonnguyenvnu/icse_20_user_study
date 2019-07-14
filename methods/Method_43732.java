public static ExchangeMetaData adaptMetadata(List<CoinbeneSymbol> markets){
  Map<CurrencyPair,CurrencyPairMetaData> pairMeta=new HashMap<>();
  for (  CoinbeneSymbol ticker : markets) {
    pairMeta.put(new CurrencyPair(ticker.getBaseAsset(),ticker.getQuoteAsset()),new CurrencyPairMetaData(null,ticker.getMinQuantity(),null,null,null));
  }
  return new ExchangeMetaData(pairMeta,null,null,null,null);
}
