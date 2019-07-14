public SymbolTickResponse getKucoin24hrStats(CurrencyPair pair) throws IOException {
  return classifyingExceptions(() -> symbolApi.getMarketStats(KucoinAdapters.adaptCurrencyPair(pair)));
}
