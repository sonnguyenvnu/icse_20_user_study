@Override public List<Ticker> getTickers(Params params) throws IOException {
  if (!(params instanceof CurrencyPairsParam)) {
    throw new IllegalArgumentException("Params must be instance of CurrencyPairsParam");
  }
  Collection<CurrencyPair> pairs=((CurrencyPairsParam)params).getCurrencyPairs();
  Set<Currency> baseSymbols=new HashSet<>();
  Set<Currency> convertSymbols=new HashSet<>();
  for (  CurrencyPair pair : pairs) {
    baseSymbols.add(pair.base);
    convertSymbols.add(pair.counter);
  }
  Map<String,CmcTicker> cmcTickerMap=super.getCmcLatestQuotes(baseSymbols,convertSymbols);
  return CmcAdapter.adaptTickerMap(cmcTickerMap);
}
