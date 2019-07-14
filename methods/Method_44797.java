@Override public List<Ticker> getTickers(Params params) throws IOException {
  if (!(params instanceof CurrencyPairsParam)) {
    throw new IllegalArgumentException("Params must be instance of CurrencyPairsParam");
  }
  CurrencyPairsParam pairs=(CurrencyPairsParam)params;
  QuoineProduct[] products=getQuoineProducts();
  return Arrays.stream(products).filter(product -> pairs.getCurrencyPairs().stream().anyMatch(pair -> product.getBaseCurrency().equals(pair.base.getCurrencyCode()) && product.getQuotedCurrency().equals(pair.counter.getCurrencyCode()))).map(product -> QuoineAdapters.adaptTicker(product,buildCurrencyPair(product))).collect(Collectors.toList());
}
