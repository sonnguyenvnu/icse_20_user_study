public VircurexLastTrade getVircurexTicker(CurrencyPair currencyPair) throws IOException {
  VircurexLastTrade vircurexLastTrade=vircurexAuthenticated.getLastTrade(currencyPair.base.getCurrencyCode().toLowerCase(),currencyPair.counter.getCurrencyCode().toLowerCase());
  return vircurexLastTrade;
}
