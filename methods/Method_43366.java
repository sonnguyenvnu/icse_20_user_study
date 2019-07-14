@Override public List<Ticker> getTickers(Params params) throws IOException {
  try {
    List<CurrencyPair> currencyPairs=(params instanceof CurrencyPairsParam) ? new ArrayList<>(((CurrencyPairsParam)params).getCurrencyPairs()) : new ArrayList<>();
    return getBittrexMarketSummaries().stream().map(bittrexMarketSummary -> BittrexAdapters.adaptTicker(bittrexMarketSummary,BittrexUtils.toCurrencyPair(bittrexMarketSummary.getMarketName()))).filter(ticker -> currencyPairs.size() == 0 || currencyPairs.contains(ticker.getCurrencyPair())).collect(Collectors.toList());
  }
 catch (  BittrexException e) {
    throw BittrexErrorAdapter.adapt(e);
  }
}
