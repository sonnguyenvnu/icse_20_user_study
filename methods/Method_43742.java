public CoinbeneOrderBook.Container getCoinbeneOrderBook(CurrencyPair currencyPair,Integer size) throws IOException {
  try {
    return checkSuccess(coinbene.orderBook(CoinbeneAdapters.adaptCurrencyPair(currencyPair),size));
  }
 catch (  CoinbeneException e) {
    throw new ExchangeException(e.getMessage(),e);
  }
}
