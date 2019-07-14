@Override public List<Ticker> getTickers(Params params) throws IOException {
  try {
    final List<CurrencyPair> currencyPairs=new ArrayList<>();
    if (params instanceof CurrencyPairsParam) {
      currencyPairs.addAll(((CurrencyPairsParam)params).getCurrencyPairs());
    }
    return getTickersRaw().stream().map(livecoinTicker -> LivecoinAdapters.adaptTicker(livecoinTicker,CurrencyPairDeserializer.getCurrencyPairFromString(livecoinTicker.getSymbol()))).filter(ticker -> currencyPairs.size() == 0 || currencyPairs.contains(ticker.getCurrencyPair())).collect(Collectors.toList());
  }
 catch (  LivecoinException e) {
    throw LivecoinErrorAdapter.adapt(e);
  }
}
