public List<BitmexKline> getBucketedTrades(String binSize,Boolean partial,CurrencyPair pair,long count,Boolean reverse) throws ExchangeException {
  String bitmexSymbol=BitmexAdapters.adaptCurrencyPairToSymbol(pair);
  return updateRateLimit(() -> bitmex.getBucketedTrades(binSize,partial,bitmexSymbol,count,reverse));
}
