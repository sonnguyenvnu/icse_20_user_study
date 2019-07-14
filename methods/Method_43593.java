public CexIODepth getCexIOOrderBook(CurrencyPair currencyPair) throws IOException {
  CexIODepth cexIODepth=cexio.getDepth(currencyPair.base.getCurrencyCode(),currencyPair.counter.getCurrencyCode());
  return cexIODepth;
}
