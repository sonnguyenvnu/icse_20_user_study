public BankeraOrderBook getOrderbook(CurrencyPair currencyPair) throws IOException {
  try {
    return bankera.getOrderbook(getMarketNameFromPair(currencyPair));
  }
 catch (  BankeraException e) {
    throw BankeraAdapters.adaptError(e);
  }
}
