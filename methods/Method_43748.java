public CoindealOrderBook getCoindealOrderbook(CurrencyPair currencyPair) throws IOException {
  try {
    return coindeal.getOrderBook(CoindealAdapters.currencyPairToString(currencyPair));
  }
 catch (  CoindealException e) {
    throw new ExchangeException(e.getMessage());
  }
}
