public VircurexDepth getVircurexOrderBook(CurrencyPair currencyPair) throws IOException {
  VircurexDepth vircurexDepth=vircurexAuthenticated.getFullDepth(currencyPair.base.getCurrencyCode().toLowerCase(),currencyPair.counter.getCurrencyCode().toLowerCase());
  return vircurexDepth;
}
