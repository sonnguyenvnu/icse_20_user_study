@Override public List<Ticker> getTickers(Params params) throws IOException {
  try {
    final List<CurrencyPair> currencyPairs=new ArrayList<>();
    if (params instanceof CurrencyPairsParam) {
      currencyPairs.addAll(((CurrencyPairsParam)params).getCurrencyPairs());
    }
    return getCryptopiaMarkets().stream().map(cryptopiaTicker -> CryptopiaAdapters.adaptTicker(cryptopiaTicker,CurrencyPairDeserializer.getCurrencyPairFromString(cryptopiaTicker.getLabel()))).filter(ticker -> currencyPairs.size() == 0 || currencyPairs.contains(ticker.getCurrencyPair())).collect(Collectors.toList());
  }
 catch (  CryptopiaException e) {
    throw CryptopiaErrorAdapter.adapt(e);
  }
}
